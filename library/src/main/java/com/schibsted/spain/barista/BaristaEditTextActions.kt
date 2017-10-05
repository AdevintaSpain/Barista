package com.schibsted.spain.barista

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.replaceText
import com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId
import com.schibsted.spain.barista.BaristaScrollActions.safelyScrollTo

object BaristaEditTextActions {

    @JvmStatic
    fun writeToEditText(@IdRes id: Int, text: String) {
        safelyScrollTo(id)

        onView(displayedWithId(id)).perform(replaceText(text))
    }
}
