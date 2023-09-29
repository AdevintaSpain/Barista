package com.adevinta.android.barista.assertion

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.adevinta.android.barista.interaction.BaristaListInteractions.findListViewMatcher
import com.adevinta.android.barista.interaction.BaristaListInteractions.findRecyclerMatcher
import com.adevinta.android.barista.interaction.BaristaListInteractions.scrollListToPosition
import com.adevinta.android.barista.internal.failurehandler.SpyFailureHandler
import com.adevinta.android.barista.internal.matcher.DrawableMatcher
import com.adevinta.android.barista.internal.matcher.ListViewItemCountAssertion
import com.adevinta.android.barista.internal.matcher.ListViewNotEmptyAssertion
import com.adevinta.android.barista.internal.matcher.RecyclerViewItemCountAssertion
import com.adevinta.android.barista.internal.matcher.RecyclerViewNotEmptyAssertion
import com.adevinta.android.barista.internal.matcher.withCompatText
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

object BaristaListAssertions {
  private const val NO_VIEW_ID: Int = -1

  @JvmStatic
  fun assertListItemCount(@IdRes listId: Int, expectedItemCount: Int) {
    val spyFailureHandler = SpyFailureHandler()
    val recyclerMatcher = findRecyclerMatcher(listId)
    val listViewMatcher = findListViewMatcher(listId)

    try {
      Espresso.onView(recyclerMatcher).withFailureHandler(spyFailureHandler).check(RecyclerViewItemCountAssertion(expectedItemCount))
    } catch (noRecyclerMatching: NoMatchingViewException) {
      try {
        Espresso.onView(listViewMatcher).withFailureHandler(spyFailureHandler).check(ListViewItemCountAssertion(expectedItemCount))
      } catch (listViewError: Throwable) {
        spyFailureHandler.resendLastError("Item count mismatch on ListView. Expected $expectedItemCount items in the list.")
      }
    } catch (recyclerError: Throwable) {
      spyFailureHandler.resendLastError("Item count mismatch on RecyclerView. Expected $expectedItemCount items in the list.")
    }
  }

  @JvmStatic
  fun assertListNotEmpty(@IdRes listId: Int) {
    val spyFailureHandler = SpyFailureHandler()
    val recyclerMatcher = findRecyclerMatcher(listId)
    val listViewMatcher = findListViewMatcher(listId)

    try {
      Espresso.onView(recyclerMatcher).withFailureHandler(spyFailureHandler).check(RecyclerViewNotEmptyAssertion())
    } catch (noRecyclerMatching: NoMatchingViewException) {
      try {
        Espresso.onView(listViewMatcher).withFailureHandler(spyFailureHandler).check(ListViewNotEmptyAssertion())
      } catch (listViewError: Throwable) {
        spyFailureHandler.resendLastError("Item count mismatch on ListView. Expected one or more items in the list.")
      }
    } catch (recyclerError: Throwable) {
      spyFailureHandler.resendLastError("Item count mismatch on RecyclerView. Expected one or more items in the list.")
    }
  }

  @JvmStatic
  fun assertDisplayedAtPosition(@IdRes listId: Int, position: Int, text: String) {
    assertDisplayedAtPosition(listId = listId, position = position, targetViewId = NO_VIEW_ID, text = text)
  }

  @JvmStatic
  fun assertDisplayedAtPosition(@IdRes listId: Int, position: Int, @IdRes targetViewId: Int = NO_VIEW_ID, text: String) {
    assertCustomAssertionAtPosition(
      listId = listId,
      position = position,
      targetViewId = targetViewId,
      viewAssertion = ViewAssertions.matches(
        Matchers.anyOf(
          ViewMatchers.withChild(withCompatText(text)),
          withCompatText(text)
        )
      )
    )
  }

  @JvmStatic
  fun assertDisplayedAtPosition(@IdRes listId: Int, position: Int, @StringRes textId: Int) {
    assertDisplayedAtPosition(listId = listId, position = position, targetViewId = NO_VIEW_ID, textId = textId)
  }

  @JvmStatic
  fun assertDisplayedAtPosition(@IdRes listId: Int, position: Int, @IdRes targetViewId: Int = NO_VIEW_ID, @StringRes textId: Int) {
    assertCustomAssertionAtPosition(
      listId = listId,
      position = position,
      targetViewId = targetViewId,
      viewAssertion = ViewAssertions.matches(
        Matchers.anyOf(
          ViewMatchers.withChild(ViewMatchers.withText(textId)),
          ViewMatchers.withText(textId)
        )
      )
    )
  }

  @JvmStatic
  fun assertDrawableDisplayedAtPosition(
    @IdRes listId: Int,
    position: Int,
    @IdRes targetViewId: Int = NO_VIEW_ID,
    @DrawableRes drawableRes: Int
  ) {
    scrollListToPosition(listId, position)

    assertCustomAssertionAtPosition(
      listId = listId,
      position = position,
      targetViewId = targetViewId,
      viewAssertion = ViewAssertions.matches(
        Matchers.anyOf(
          ViewMatchers.hasDescendant(DrawableMatcher.withDrawable(drawableRes)),
          DrawableMatcher.withDrawable(drawableRes)
        )
      )
    )
  }

  @JvmStatic
  fun assertCustomAssertionAtPosition(
    @IdRes listId: Int,
    position: Int,
    @IdRes targetViewId: Int = NO_VIEW_ID,
    viewAssertion: ViewAssertion
  ) {
    scrollListToPosition(listId, position)

    Espresso.onView(
      atPositionOnList(
        listId = listId,
        position = position,
        targetViewId = targetViewId
      )
    )
      .check(viewAssertion)
  }

  private fun atPositionOnList(@IdRes listId: Int, position: Int, @IdRes targetViewId: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("Text not found in list with id $listId at position $position")
      }

      override fun matchesSafely(view: View): Boolean {
        val listView: View? = view.rootView.findViewById(listId)

        listView?.let {
          return when (it) {
            is RecyclerView -> matchRecyclerView(
              listId,
              position,
              targetViewId,
              view
            )
            is AbsListView -> matchListView(listId, position, targetViewId, view)
            else -> false
          }
        } ?: return false
      }
    }
  }

  private fun matchListView(@IdRes listViewId: Int, position: Int, @IdRes targetViewId: Int, view: View): Boolean {
    var childView: View? = null

    if (childView == null) {
      val views =
       getShownViewsById(view.rootView as ViewGroup, listViewId)
      if (views != null && views.isNotEmpty()) {
        val listView: AbsListView = views[0] as AbsListView
        if (listView.id == listViewId) {
          val positionOnScreen = position - listView.firstVisiblePosition
          val viewAtPosition = listView.getChildAt(positionOnScreen)

          viewAtPosition?.let {
            childView = it
          }
        } else {
          return false
        }
      } else {
        return false
      }
    }

    return if (targetViewId == NO_VIEW_ID) {
      view == childView
    } else {
      val targetView: View? = childView?.findViewById(targetViewId)
      view == targetView
    }
  }

  private fun matchRecyclerView(@IdRes recyclerViewId: Int, position: Int, @IdRes targetViewId: Int, view: View): Boolean {
    var childView: View? = null

    if (childView == null) {
      val views = getShownViewsById(view.rootView as ViewGroup, recyclerViewId)
      if (views != null && views.isNotEmpty()) {
        val recyclerView: RecyclerView = views[0] as RecyclerView
        if (recyclerView.id == recyclerViewId) {
          val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
          viewHolder?.let { checkedViewHolder ->
            childView = checkedViewHolder.itemView
          }
        } else {
          return false
        }
      } else {
        return false
      }
    }

    return if (targetViewId == NO_VIEW_ID) {
      view == childView
    } else {
      val targetView: View? = childView?.findViewById(targetViewId)
      view == targetView
    }
  }

  private fun getShownViewsById(root: ViewGroup, viewId: Int): ArrayList<View>? {
    val views = ArrayList<View>()
    val childCount = root.childCount
    for (i in 0 until childCount) {
      val child = root.getChildAt(i)
      if (child is ViewGroup) {
        views.addAll(getShownViewsById(child, viewId)!!)
      }
      val childId = child.id
      if (childId == viewId && isShowOnScreen(child)) {
        views.add(child)
      }
    }
    return views
  }

  private fun isShowOnScreen(view: View): Boolean {
    if (!view.isShown) {
      return false
    }
    val actualPosition = Rect().also { view.getGlobalVisibleRect(it) }
    val screen = Resources.getSystem().displayMetrics.run {
      Rect(0, 0, widthPixels, heightPixels)
    }
    return actualPosition.intersect(screen)
  }
}