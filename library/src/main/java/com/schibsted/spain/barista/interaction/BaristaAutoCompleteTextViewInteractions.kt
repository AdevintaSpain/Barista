package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.interaction.BaristaScrollInteractions.safelyScrollTo
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedAnd
import com.schibsted.spain.barista.internal.util.KeyboardAction
import com.schibsted.spain.barista.internal.util.findAction
import com.schibsted.spain.barista.internal.viewaction.AutoCompleteViewActions.replaceAutoComplete

object BaristaAutoCompleteTextViewInteractions {

  @JvmStatic
  @JvmOverloads
  fun writeToAutoComplete(@IdRes autoCompleteId: Int, text: String, action: KeyboardAction? = KeyboardAction.CLOSE) {
    safelyScrollTo(autoCompleteId)
    onView(displayedAnd(withId(autoCompleteId))).perform(replaceAutoComplete(text), findAction(action))
  }
}
