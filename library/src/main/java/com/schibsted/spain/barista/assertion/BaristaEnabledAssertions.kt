@file:JvmName("BaristaEnabledAssertions")

package com.schibsted.spain.barista.assertion

import android.support.test.espresso.matcher.ViewMatchers.isEnabled
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.Matchers.not

fun assertEnabled(resId: Int) {
  resId.resourceMatcher().assertAny(isEnabled())
}

fun assertEnabled(text: String) {
  withText(text).assertAny(isEnabled())
}

fun assertDisabled(resId: Int) {
  resId.resourceMatcher().assertAny(not(isEnabled()))
}

fun assertDisabled(text: String) {
  withText(text).assertAny(not(isEnabled()))
}
