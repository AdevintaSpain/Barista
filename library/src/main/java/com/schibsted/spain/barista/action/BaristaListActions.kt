package com.schibsted.spain.barista.action

import android.support.annotation.IdRes
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.AmbiguousViewMatcherException
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.FailureHandler
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import android.widget.ListView
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.withFailureHandler
import com.schibsted.spain.barista.internal.viewaction.PerformClickAction.clickUsingPerformClick
import org.hamcrest.Matchers.*

object BaristaListActions {

    @JvmStatic
    @JvmOverloads
    fun clickListItem(@IdRes id: Int? = null, position: Int) {
        verifyOneSingleMatch(id)

        val spyFailureHandler = SpyFailureHandler()
        try {
            clickRecycler(id, position, spyFailureHandler)
        } catch (noRecyclerMatching: NoMatchingViewException) {
            clickListView(id, position, spyFailureHandler)
        }
    }

    private fun clickRecycler(@IdRes id: Int?, position: Int, failureHandler: FailureHandler) {
        val matcher = findRecyclerMatcher(id)
        onView(matcher)
                .withFailureHandler(failureHandler)
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, clickUsingPerformClick()))
    }

    private fun clickListView(@IdRes id: Int?, position: Int, failureHandler: FailureHandler) {
        val matcher = findListViewMatcher(id)
        withFailureHandler(failureHandler) {
            onData(anything())
                    .inAdapterView(matcher)
                    .atPosition(position)
                    .perform(clickUsingPerformClick())
        }
    }

    @JvmStatic
    @JvmOverloads
    fun scrollListToPosition(@IdRes id: Int? = null, position: Int) {
        verifyOneSingleMatch(id)

        val spyFailureHandler = SpyFailureHandler()
        try {
            scrollRecycler(id, spyFailureHandler, position)
        } catch (noRecyclerMatching: NoMatchingViewException) {
            scrollListView(spyFailureHandler, id, position)
        }
    }

    private fun scrollRecycler(id: Int?, spyFailureHandler: SpyFailureHandler, position: Int) {
        onView(findRecyclerMatcher(id))
                .withFailureHandler(spyFailureHandler)
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
    }

    private fun scrollListView(spyFailureHandler: SpyFailureHandler, id: Int?, position: Int) {
        withFailureHandler(spyFailureHandler) {
            onData(anything())
                    .inAdapterView(findListViewMatcher(id))
                    .atPosition(position)
                    .perform(scrollTo())
        }
    }

    @JvmStatic
    fun clickListItemChild(position: Int, @IdRes childId: Int) {
        TODO()
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

    private fun findRecyclerMatcher(@IdRes id: Int?) = when (id) {
        null -> allOf(isDisplayed(), isAssignableFrom(RecyclerView::class.java))
        else -> allOf(isDisplayed(), isAssignableFrom(RecyclerView::class.java), withId(id))
    }

    private fun findListViewMatcher(@IdRes id: Int?) = when (id) {
        null -> allOf(isDisplayed(), isAssignableFrom(ListView::class.java))
        else -> allOf(isDisplayed(), isAssignableFrom(ListView::class.java), withId(id))
    }

    private fun resourceName(resId: Int) = InstrumentationRegistry.getTargetContext().resources.getResourceName(resId)
}