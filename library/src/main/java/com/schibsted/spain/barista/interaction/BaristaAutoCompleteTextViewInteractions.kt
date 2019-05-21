package com.schibsted.spain.barista.interaction

import androidx.annotation.IdRes
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import android.widget.EditText
import com.schibsted.spain.barista.internal.performAction
import com.schibsted.spain.barista.internal.viewaction.AutoCompleteViewActions.replaceAutoComplete
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf

object BaristaAutoCompleteTextViewInteractions {

  @JvmStatic
  fun writeToAutoComplete(@IdRes autoCompleteId: Int, text: String) {
    val withId = withId(autoCompleteId)
    val assignableFrom = isAssignableFrom(EditText::class.java)
    val simpleMatcher = allOf(withId, assignableFrom)
    val wrapperMatcher = allOf(isDescendantOfA(withId), assignableFrom)
    val combinedMatcher = anyOf(simpleMatcher, wrapperMatcher)
    combinedMatcher.performAction(replaceAutoComplete(text))
  }
}
