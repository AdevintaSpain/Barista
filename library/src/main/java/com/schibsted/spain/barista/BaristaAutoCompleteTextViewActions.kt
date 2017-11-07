package com.schibsted.spain.barista

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.BaristaScrollActions.safelyScrollTo
import com.schibsted.spain.barista.custom.AutocompleteViewActions.replaceAutocomplete
import com.schibsted.spain.barista.custom.DisplayedMatchers.displayedAnd

object BaristaAutoCompleteTextViewActions {

    @JvmStatic
    fun writeToAutoCompleteTextView(@IdRes id: Int, text: String) {
        safelyScrollTo(id)
        onView(displayedAnd(withId(id))).perform(replaceAutocomplete(text))
    }
}
