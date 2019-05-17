package com.schibsted.spain.barista.interaction

import androidx.annotation.IdRes
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import android.widget.EditText
import com.schibsted.spain.barista.internal.performAction
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf

object BaristaEditTextInteractions {

  @JvmStatic
  fun writeTo(@IdRes editTextId: Int, text: String) {
    val withId = withId(editTextId)
    val assignableFrom = isAssignableFrom(EditText::class.java)
    val simpleMatcher = allOf(withId, assignableFrom)
    val wrapperMatcher = allOf(isDescendantOfA(withId), assignableFrom)
    val combinedMatcher = anyOf(simpleMatcher, wrapperMatcher)
    combinedMatcher.performAction(replaceText(text))
  }

  @JvmStatic
  fun clearText(@IdRes editTextId: Int) {
    withId(editTextId).performAction(clearText())
  }
}
