package com.adevinta.android.barista.interaction

import androidx.annotation.IdRes
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.internal.matcher.HelperMatchers.withParentId
import com.adevinta.android.barista.internal.performAction
import com.schibsted.spain.barista.internal.matcher.withCompatText
import org.hamcrest.Matchers.allOf

object BaristaRadioButtonInteractions {

  @JvmStatic
  fun clickRadioButtonItem(@IdRes radioGroupId: Int, @IdRes itemToClickId: Int) {
    allOf(withParentId(radioGroupId), withId(itemToClickId))
        .performAction(click())
  }

  @JvmStatic
  fun clickRadioButtonItem(@IdRes radioGroupId: Int, text: String) {
    allOf(withParentId(radioGroupId), withCompatText(text))
        .performAction(click())
  }

  @JvmStatic
  fun clickRadioButtonPosition(@IdRes radioGroupId: Int, position: Int) {
    allOf(withParentId(radioGroupId), ViewMatchers.withParentIndex(position))
        .performAction(click())
  }
}
