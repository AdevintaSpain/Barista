package com.schibsted.spain.barista.action

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.action.BaristaScrollActions.safelyScrollTo
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedAnd
import com.schibsted.spain.barista.internal.viewaction.AutoCompleteViewActions.replaceAutoComplete

object BaristaAutoCompleteTextViewActions {

    @JvmStatic
    fun writeToAutoComplete(@IdRes autoCompleteId: Int, text: String) {
        safelyScrollTo(autoCompleteId)
        onView(displayedAnd(withId(autoCompleteId))).perform(replaceAutoComplete(text))
    }
}
