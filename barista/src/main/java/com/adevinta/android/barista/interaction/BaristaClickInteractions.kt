package com.adevinta.android.barista.interaction

import android.view.View
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import com.adevinta.android.barista.internal.matcher.withCompatText
import com.adevinta.android.barista.internal.performAction
import com.adevinta.android.barista.internal.util.resourceMatcher
import org.hamcrest.Matcher

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

  /**
   * Click on the matched view
   */
  @JvmStatic
  fun clickOn(matcher: Matcher<View>) {
    matcher.performAction(click())
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
