package com.adevinta.android.barista.interaction

import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import com.adevinta.android.barista.internal.performAction
import com.adevinta.android.barista.internal.util.resourceMatcher
import com.adevinta.android.barista.internal.matcher.withCompatText

object BaristaClickInteractions {

  @JvmStatic
  fun clickBack() {
    pressBack()
  }

  @JvmStatic
  fun clickOn(resId: Int) {
    resId.resourceMatcher().performAction(click())
  }

  @JvmStatic
  fun clickOn(text: String) {
    withCompatText(text).performAction(click())
  }

  @JvmStatic
  fun longClickOn(resId: Int) {
    resId.resourceMatcher().performAction(longClick())
  }

  @JvmStatic
  fun longClickOn(text: String) {
    withCompatText(text).performAction(longClick())
  }
}
