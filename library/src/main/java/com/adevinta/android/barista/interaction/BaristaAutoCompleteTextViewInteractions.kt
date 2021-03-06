package com.adevinta.android.barista.interaction

import android.widget.EditText
import androidx.annotation.IdRes
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.internal.performAction
import com.adevinta.android.barista.internal.viewaction.AutoCompleteViewActions.replaceAutoComplete
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
