package com.adevinta.android.barista.interaction

import androidx.annotation.IdRes
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.internal.performAction
import com.adevinta.android.barista.internal.viewaction.CheckableViewActions.setCheckedState

object BaristaCheckboxInteractions {

  @JvmStatic
  fun check(@IdRes checkboxId: Int) {
    withId(checkboxId).performAction(setCheckedState(true))
  }

  @JvmStatic
  fun uncheck(@IdRes checkboxId: Int) {
    withId(checkboxId).performAction(setCheckedState(false))
  }
}