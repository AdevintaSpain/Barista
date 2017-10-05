package com.schibsted.spain.barista

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import android.widget.AdapterView
import com.schibsted.spain.barista.custom.PerformClickAction.clickUsingPerformClick
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.withFailureHandler
import org.hamcrest.Matchers.*

object BaristaListViewActions {

    @JvmStatic
    fun clickListViewItem(@IdRes listViewId: Int, vararg positions: Int) {
        check(positions.isNotEmpty(), { "Positions cannot be empty" })
        positions.forEach {
            clickItem(listViewId, it)
        }
    }

    @JvmStatic
    fun clickListViewItem(@IdRes listViewId: Int, modelClass: Class<*>, vararg positions: Int) {
        check(positions.isNotEmpty(), { "Positions cannot be empty" })
        positions.forEach {
            clickItem(listViewId, it, modelClass)
        }
    }

    private fun clickItem(@IdRes listViewId: Int, position: Int) {
        val spyHandler = SpyFailureHandler()
        try {
            withFailureHandler(spyHandler) { clickListItemForSingleListOnScreen(position) }
        } catch (e: NoMatchingViewException) {
            try {
                withFailureHandler(spyHandler) { clickListItemForMultipleListsOnScreen(listViewId, position) }
            } catch (e: Throwable) {
                spyHandler.resendLastError("Could not click on list item $position")
            }
        }
    }

    private fun clickItem(@IdRes listViewId: Int, position: Int, modelClass: Class<*>) {
        val spyHandler = SpyFailureHandler()
        try {
            withFailureHandler(spyHandler) { clickListItemForMultipleListsOnScreen(listViewId, position, modelClass) }
        } catch (e: NoMatchingViewException) {
            try {
                withFailureHandler(spyHandler) { clickListItemForSingleListOnScreen(position, modelClass) }
            } catch (e: Throwable) {
                spyHandler.resendLastError("Could not click on list item $position of type ${modelClass.simpleName}")
            }
        }

    }

    private fun clickListItemForMultipleListsOnScreen(@IdRes listViewId: Int, position: Int) {
        // This method only seems to work when there are multiple ListViews in the same activity
        onData(anything()).inAdapterView(
                allOf<View>(
                        isAssignableFrom(AdapterView::class.java),
                        isDescendantOfA(withId(listViewId)),
                        isDisplayed()))
                .atPosition(position)
                .perform(clickUsingPerformClick())
    }

    private fun clickListItemForMultipleListsOnScreen(@IdRes listViewId: Int, position: Int, modelClass: Class<*>) {
        // This method only seems to work when there are multiple ListViews in the same activity
        onData(`is`(instanceOf(modelClass))).inAdapterView(
                allOf<View>(
                        isAssignableFrom(AdapterView::class.java),
                        isDescendantOfA(withId(listViewId)),
                        isDisplayed()))
                .atPosition(position)
                .perform(clickUsingPerformClick())
    }

    private fun clickListItemForSingleListOnScreen(position: Int) {
        // This method only seems to work when there is just one ListView in the same activity
        onData(anything())
                .atPosition(position)
                .perform(clickUsingPerformClick())
    }

    private fun clickListItemForSingleListOnScreen(position: Int, modelClass: Class<*>) {
        // This method only seems to work when there is just one ListView in the same activity
        onData(`is`(instanceOf(modelClass)))
                .atPosition(position)
                .perform(clickUsingPerformClick())
    }
}
