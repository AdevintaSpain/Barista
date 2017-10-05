package com.schibsted.spain.barista

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import com.schibsted.spain.barista.custom.AutocompleteViewActions.replaceAutocomplete
import com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId
import com.schibsted.spain.barista.BaristaScrollActions.safelyScrollTo

object BaristaAutoCompleteTextViewActions {

    @JvmStatic
    fun writeToAutoCompleteTextView(@IdRes id: Int, text: String) {
        safelyScrollTo(id)

        onView(displayedWithId(id)).perform(replaceAutocomplete(text))
    }
}
