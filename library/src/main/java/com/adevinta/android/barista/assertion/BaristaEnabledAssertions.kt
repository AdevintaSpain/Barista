package com.adevinta.android.barista.assertion

import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import com.adevinta.android.barista.internal.assertAny
import com.adevinta.android.barista.internal.util.resourceMatcher
import com.adevinta.android.barista.internal.matcher.withCompatText
import org.hamcrest.Matchers.not

object BaristaEnabledAssertions {

  @JvmStatic
  fun assertEnabled(resId: Int) {
    resId.resourceMatcher().assertAny(isEnabled())
  }

  @JvmStatic
  fun assertEnabled(text: String) {
    withCompatText(text).assertAny(isEnabled())
  }

  @JvmStatic
  fun assertDisabled(resId: Int) {
    resId.resourceMatcher().assertAny(not(isEnabled()))
  }

  @JvmStatic
  fun assertDisabled(text: String) {
    withCompatText(text).assertAny(not(isEnabled()))
  }
}