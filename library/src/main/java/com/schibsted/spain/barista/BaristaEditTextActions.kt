package com.schibsted.spain.barista

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.BaristaScrollActions.safelyScrollTo
import com.schibsted.spain.barista.custom.DisplayedMatchers.displayedAnd

object BaristaEditTextActions {

    @JvmStatic
    fun writeToEditText(@IdRes id: Int, text: String) {
        safelyScrollTo(id)
        onView(displayedAnd(withId(id))).perform(replaceText(text))
    }
}
