package com.schibsted.spain.barista.interaction

import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.longClick
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.util.ViewActionExecutor
import com.schibsted.spain.barista.internal.util.resourceMatcher

object BaristaClickInteractions {

  @JvmStatic
  fun clickBack() {
    pressBack()
  }

  @JvmStatic
  fun clickOn(resId: Int) {
    ViewActionExecutor.performAction(resId.resourceMatcher(), click())
  }

  @JvmStatic
  fun clickOn(text: String) {
    ViewActionExecutor.performAction(withText(text), click())
  }

  @JvmStatic
  fun longClickOn(resId: Int) {
    ViewActionExecutor.performAction(resId.resourceMatcher(), longClick())
  }

  @JvmStatic
  fun longClickOn(text: String) {
    ViewActionExecutor.performAction(withText(text), longClick())
  }
}
