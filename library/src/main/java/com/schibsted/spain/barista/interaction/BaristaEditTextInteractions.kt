package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.action.ViewActions.clearText
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.matcher.ViewMatchers.*
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
