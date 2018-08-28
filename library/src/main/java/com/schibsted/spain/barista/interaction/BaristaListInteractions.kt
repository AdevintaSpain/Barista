package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.AmbiguousViewMatcherException
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.FailureHandler
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.widget.AbsListView
import android.widget.ListView
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import com.schibsted.spain.barista.internal.failurehandler.withFailureHandler
import com.schibsted.spain.barista.internal.matcher.ListViewItemCountAssertion
import com.schibsted.spain.barista.internal.matcher.RecyclerViewItemCountAssertion
import com.schibsted.spain.barista.internal.viewaction.ClickChildAction.clickChildWithId
import com.schibsted.spain.barista.internal.viewaction.PerformClickAction.clickUsingPerformClick
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher

object BaristaListInteractions {

    const val NO_VIEW_ID: Int = -1

    @JvmStatic
    @JvmOverloads
    fun clickListItem(@IdRes id: Int? = null, position: Int) {
        performMagicAction(id, position,
                recyclerAction = actionOnItemAtPosition<ViewHolder>(position, clickUsingPerformClick()),
                listViewAction = clickUsingPerformClick()
        )
    }

    @JvmStatic
    @JvmOverloads
    fun scrollListToPosition(@IdRes id: Int? = null, position: Int) {
        performMagicAction(id, position,
                recyclerAction = scrollToPosition<ViewHolder>(position),
                listViewAction = scrollTo()
        )
    }

    @JvmStatic
    @JvmOverloads
    fun clickListItemChild(@IdRes id: Int? = null, position: Int, @IdRes childId: Int) {
        performMagicAction(id, position,
                recyclerAction = actionOnItemAtPosition<ViewHolder>(position, clickChildWithId(childId)),
                listViewAction = clickChildWithId(childId)
        )
    }

    @JvmStatic
    fun assertListItemCount(@IdRes listId: Int, expectedItemCount: Int) {
        val spyFailureHandler = SpyFailureHandler()
        val recyclerMatcher = findRecyclerMatcher(listId)
        val listViewMatcher = findListViewMatcher(listId)

        try {
            onView(recyclerMatcher).check(RecyclerViewItemCountAssertion(expectedItemCount))
        } catch (noRecyclerMatching: NoMatchingViewException) {
            try {
                onView(listViewMatcher).check(ListViewItemCountAssertion(expectedItemCount))
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

        onView(atPositionOnList(listId = listId,
                position = position,
                targetViewId = targetViewId))
                .check(matches(CoreMatchers.anyOf(withChild(withText(text)), withText(text))))
    }

    private fun performMagicAction(@IdRes id: Int?, position: Int, recyclerAction: ViewAction, listViewAction: ViewAction) {
        verifyOneSingleMatch(id)

        val recyclerMatcher = findRecyclerMatcher(id)
        val listViewMatcher = findListViewMatcher(id)
        val spyFailureHandler = SpyFailureHandler()
        try {
            performOnRecycler(recyclerMatcher, recyclerAction, spyFailureHandler)
        } catch (noRecyclerMatching: NoMatchingViewException) {
          try {
            performOnListView(listViewMatcher, position, listViewAction, spyFailureHandler)
          } catch (listViewError: Throwable) {
            spyFailureHandler.resendLastError("Could not perform action (${listViewAction.description}) on ListView ${listViewMatcher.description()}")
          }
        } catch (recyclerError: Throwable) {
            spyFailureHandler.resendLastError("Could not perform action (${recyclerAction.description}) on RecyclerView ${recyclerMatcher.description()}")
        }
    }

    private fun verifyOneSingleMatch(@IdRes id: Int?) {
        val spyFailureHandler = SpyFailureHandler()
        try {
            onView(anyOf(findListViewMatcher(id), findRecyclerMatcher(id)))
                    .withFailureHandler(spyFailureHandler)
                    .check(matches(isDisplayed()))
        } catch (multipleViews: AmbiguousViewMatcherException) {
            spyFailureHandler.resendLastError("There are multiple ListView or RecyclerView in the hierarchy. You must specify an id parameter using clickListItem(id, position)")
        } catch (noneFound: NoMatchingViewException) {
            val message = if (id == null) {
                "No ListView or RecyclerView found in the hierarchy"
            } else {
                "No ListView or RecyclerView with id ${resourceName(id)} was found in the hierarchy. Did you use a wrong id?"
            }
            spyFailureHandler.resendLastError(message)
        }
    }

    private fun performOnRecycler(matcher: Matcher<View>, recyclerAction: ViewAction, failureHandler: FailureHandler) {
        onView(matcher)
                .withFailureHandler(failureHandler)
                .perform(recyclerAction)
    }

    private fun performOnListView(matcher: Matcher<View>, position: Int, listViewAction: ViewAction, failureHandler: FailureHandler) {
        withFailureHandler(failureHandler) {
            onData(anything())
                    .inAdapterView(matcher)
                    .atPosition(position)
                    .perform(listViewAction)
        }
    }

    private fun findRecyclerMatcher(@IdRes id: Int?) = when (id) {
        null -> allOf(isDisplayed(), isAssignableFrom(RecyclerView::class.java))
        else -> allOf(isDisplayed(), isAssignableFrom(RecyclerView::class.java), withId(id))
    }

    private fun findListViewMatcher(@IdRes id: Int?) = when (id) {
        null -> allOf(isDisplayed(), isAssignableFrom(AbsListView::class.java))
        else -> allOf(isDisplayed(), isAssignableFrom(AbsListView::class.java), withId(id))
    }

    private fun resourceName(resId: Int) = InstrumentationRegistry.getTargetContext().resources.getResourceName(resId)

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

        if (targetViewId == NO_VIEW_ID) {
            return view == childView
        } else {
            val targetView: View? = childView?.findViewById(targetViewId)
            return view == targetView
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

        if (targetViewId == NO_VIEW_ID) {
            return view == childView
        } else {
            val targetView: View? = childView?.findViewById(targetViewId)
            return view == targetView
        }
    }
}