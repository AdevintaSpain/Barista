package com.adevinta.android.barista.interaction

import android.widget.EditText
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasFocus
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.internal.performAction
import org.hamcrest.Matchers.allOf

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
