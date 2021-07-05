package com.adevinta.android.barista.interaction

import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.adevinta.android.barista.internal.performAction
import com.adevinta.android.barista.internal.util.resourceMatcher

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
    withText(text).performAction(click())
  }

  @JvmStatic
  fun longClickOn(resId: Int) {
    resId.resourceMatcher().performAction(longClick())
  }

  @JvmStatic
  fun longClickOn(text: String) {
    withText(text).performAction(longClick())
  }
}
