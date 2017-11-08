package com.schibsted.spain.barista.action

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.action.BaristaScrollActions.safelyScrollTo
import com.schibsted.spain.barista.internal.viewaction.AutocompleteViewActions.replaceAutocomplete
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedAnd

object BaristaAutoCompleteTextViewActions {

    @JvmStatic
    fun writeToAutoCompleteTextView(@IdRes id: Int, text: String) {
        safelyScrollTo(id)
        onView(displayedAnd(withId(id))).perform(replaceAutocomplete(text))
    }
}
