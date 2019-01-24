package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ListView
import com.schibsted.spain.barista.interaction.BaristaListInteractions.findListViewMatcher
import com.schibsted.spain.barista.interaction.BaristaListInteractions.findRecyclerMatcher
import com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.matcher.ListViewItemCountAssertion
import com.schibsted.spain.barista.internal.matcher.RecyclerViewItemCountAssertion
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
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
  fun assertDisplayedAtPosition(@IdRes listId: Int, position: Int, text: String) {
    assertDisplayedAtPosition(listId = listId, position = position, targetViewId = NO_VIEW_ID, text = text)
  }

  @JvmStatic
  fun assertDisplayedAtPosition(@IdRes listId: Int, position: Int, @IdRes targetViewId: Int = NO_VIEW_ID, text: String) {
    scrollListToPosition(listId, position)

    Espresso.onView(atPositionOnList(listId = listId,
        position = position,
        targetViewId = targetViewId))
        .check(ViewAssertions.matches(CoreMatchers.anyOf(ViewMatchers.withChild(ViewMatchers.withText(text)), ViewMatchers.withText(text))))
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
            is RecyclerView -> matchRecyclerView(listId, position, targetViewId, view)
            is ListView -> matchListView(listId, position, targetViewId, view)
            else -> false
          }
        } ?: return false
      }
    }
  }

  private fun matchListView(@IdRes listViewId: Int, position: Int, @IdRes targetViewId: Int, view: View): Boolean {
    var childView: View? = null

    if (childView == null) {
      val listView: ListView? = view.rootView.findViewById(listViewId) as ListView
      if (listView != null && listView.id == listViewId) {
        val positionOnScreen = position - listView.firstVisiblePosition
        val viewAtPosition = listView.getChildAt(positionOnScreen)

        viewAtPosition?.let {
          childView = it
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
      val recyclerView: RecyclerView? = view.rootView.findViewById(recyclerViewId) as RecyclerView
      if (recyclerView != null && recyclerView.id == recyclerViewId) {
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
        viewHolder?.let { checkedViewHolder ->
          childView = checkedViewHolder.itemView
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
}