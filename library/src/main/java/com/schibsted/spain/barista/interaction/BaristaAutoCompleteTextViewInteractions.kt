package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.internal.performAction
import com.schibsted.spain.barista.internal.viewaction.AutoCompleteViewActions.replaceAutoComplete

object BaristaAutoCompleteTextViewInteractions {

    @JvmStatic
    fun writeToAutoComplete(@IdRes autoCompleteId: Int, text: String) {
        withId(autoCompleteId).performAction(replaceAutoComplete(text))
    }
}
