package com.schibsted.spain.barista.internal.util

import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions

enum class KeyboardAction { CLOSE, SUBMIT }

fun findAction(action: KeyboardAction?): ViewAction = when (action) {
  KeyboardAction.SUBMIT -> ViewActions.pressImeActionButton()
  else -> ViewActions.closeSoftKeyboard()
}
