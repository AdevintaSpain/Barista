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
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.widget.AbsListView
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import com.schibsted.spain.barista.internal.failurehandler.withFailureHandler
import com.schibsted.spain.barista.internal.viewaction.ClickChildAction.clickChildWithId
import com.schibsted.spain.barista.internal.viewaction.PerformClickAction.clickUsingPerformClick
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf
import org.hamcrest.Matchers.anything

object BaristaListInteractions {

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
}
