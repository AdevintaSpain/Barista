package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.clearText
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.interaction.BaristaScrollInteractions.safelyScrollTo
import com.schibsted.spain.barista.internal.performAction

object BaristaEditTextInteractions {

  @JvmStatic
  fun writeTo(@IdRes editTextId: Int, text: String) {
    withId(editTextId).performAction(replaceText(text))
  }

  @JvmStatic
  fun clearText(@IdRes viewId: Int) {
    safelyScrollTo(viewId)
    onView(withId(viewId)).perform(clearText())
  }
}
