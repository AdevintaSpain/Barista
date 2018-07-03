package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.hasFocus
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.widget.EditText
import com.schibsted.spain.barista.internal.performAction
import org.hamcrest.core.AllOf.allOf

object BaristaKeyboardInteractions {

  @JvmStatic
  fun closeKeyboard() {
    onView(isRoot()).perform(closeSoftKeyboard())
  }

  @JvmStatic
  @JvmOverloads
  fun pressImeActionButton(@IdRes editTextId: Int? = null) {
    val matcher = findEditText(editTextId)
    matcher.performAction(ViewActions.pressImeActionButton())
  }

  private fun findEditText(editTextId: Int?) = when (editTextId) {
    null -> allOf(ViewMatchers.isAssignableFrom(EditText::class.java), hasFocus())
    else -> withId(editTextId)
  }
}
