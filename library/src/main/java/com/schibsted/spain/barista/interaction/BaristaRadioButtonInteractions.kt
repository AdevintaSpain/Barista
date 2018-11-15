@file:JvmName("BaristaRadioButtonInteractions")

package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.matcher.HelperMatchers.withParentId
import com.schibsted.spain.barista.internal.performAction
import org.hamcrest.Matchers.allOf

fun clickRadioButtonItem(@IdRes radioGroupId: Int, @IdRes itemToClickId: Int) {
  allOf(withParentId(radioGroupId), withId(itemToClickId))
    .performAction(click())
}

fun clickRadioButtonItem(@IdRes radioGroupId: Int, text: String) {
  allOf(withParentId(radioGroupId), withText(text))
    .performAction(click())
}

fun clickRadioButtonPosition(@IdRes radioGroupId: Int, position: Int) {
  allOf(withParentId(radioGroupId), ViewMatchers.withParentIndex(position))
    .performAction(click())
}
