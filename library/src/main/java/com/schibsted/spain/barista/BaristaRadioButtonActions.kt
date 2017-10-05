package com.schibsted.spain.barista

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import com.schibsted.spain.barista.custom.DisplayedMatchers.displayedAnd
import com.schibsted.spain.barista.custom.HelperMatchers.atPosition
import com.schibsted.spain.barista.BaristaScrollActions.safelyScrollTo
import org.hamcrest.Matchers.allOf

object BaristaRadioButtonActions {

    @JvmStatic
    fun clickRadioButtonItem(@IdRes radioGroupId: Int, @IdRes itemToClickId: Int) {
        safelyScrollTo(radioGroupId)
        onView(allOf<View>(withParent(displayedAnd(withId(radioGroupId))), displayedAnd(withId(itemToClickId))))
                .perform(click())
    }

    @JvmStatic
    fun clickRadioButtonItem(@IdRes radioGroupId: Int, text: String) {
        safelyScrollTo(radioGroupId)
        onView(allOf<View>(withParent(displayedAnd(withId(radioGroupId))), displayedAnd(withText(text))))
                .perform(click())
    }

    @JvmStatic
    fun clickRadioButtonPosition(@IdRes radioGroupId: Int, position: Int) {
        safelyScrollTo(radioGroupId)
        onView(atPosition<View>(position, withParent(displayedAnd(withId(radioGroupId)))))
                .perform(click())
    }
}
