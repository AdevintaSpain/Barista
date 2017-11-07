package com.schibsted.spain.barista

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.NoMatchingViewException
import com.schibsted.spain.barista.custom.PerformClickAction.clickUsingPerformClick
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.withFailureHandler
import org.hamcrest.Matchers.*

object BaristaListViewActions {

    @JvmStatic
    fun clickListViewItem(@IdRes listViewId: Int, vararg positions: Int) {
        check(positions.isNotEmpty(), { "Positions cannot be empty" })
        positions.forEach {
            clickItem(it)
        }
    }

    @JvmStatic
    fun clickListViewItem(@IdRes listViewId: Int, modelClass: Class<*>, vararg positions: Int) {
        check(positions.isNotEmpty(), { "Positions cannot be empty" })
        positions.forEach {
            clickItem(it, modelClass)
        }
    }

    private fun clickItem(position: Int) {
        val spyHandler = SpyFailureHandler()
        try {
            withFailureHandler(spyHandler) { clickListItemByPosition(position) }
        } catch (e: NoMatchingViewException) {
            spyHandler.resendLastError("Could not click on list item $position")
        }
    }

    private fun clickItem(position: Int, modelClass: Class<*>) {
        val spyHandler = SpyFailureHandler()
        try {
            withFailureHandler(spyHandler) { clickListItemByPositionAndClass(position, modelClass) }
        } catch (e: Throwable) {
            spyHandler.resendLastError("Could not click on list item $position of type ${modelClass.simpleName}")
        }
    }

    private fun clickListItemByPosition(position: Int) {
        // This method only seems to work when there is just one ListView in the same activity
        onData(anything())
                .atPosition(position)
                .perform(clickUsingPerformClick())
    }

    private fun clickListItemByPositionAndClass(position: Int, modelClass: Class<*>) {
        // This method only seems to work when there is just one ListView in the same activity
        onData(`is`(instanceOf(modelClass)))
                .atPosition(position)
                .perform(clickUsingPerformClick())
    }
}
