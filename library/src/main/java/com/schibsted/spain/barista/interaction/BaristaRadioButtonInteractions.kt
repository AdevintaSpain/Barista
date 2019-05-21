package com.schibsted.spain.barista.interaction

import androidx.annotation.IdRes
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.matcher.HelperMatchers.withParentId
import com.schibsted.spain.barista.internal.performAction
import org.hamcrest.Matchers.allOf

object BaristaRadioButtonInteractions {

  @JvmStatic
  fun clickRadioButtonItem(@IdRes radioGroupId: Int, @IdRes itemToClickId: Int) {
    allOf(withParentId(radioGroupId), withId(itemToClickId))
        .performAction(click())
  }

  @JvmStatic
  fun clickRadioButtonItem(@IdRes radioGroupId: Int, text: String) {
    allOf(withParentId(radioGroupId), withText(text))
        .performAction(click())
  }

  @JvmStatic
  fun clickRadioButtonPosition(@IdRes radioGroupId: Int, position: Int) {
    allOf(withParentId(radioGroupId), ViewMatchers.withParentIndex(position))
        .performAction(click())
  }
}
