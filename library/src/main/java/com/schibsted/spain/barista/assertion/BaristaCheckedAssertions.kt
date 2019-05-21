package com.schibsted.spain.barista.assertion

import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.Matchers.not

object BaristaCheckedAssertions {

  @JvmStatic
  fun assertChecked(resId: Int) {
    resId.resourceMatcher().assertAny(isChecked())
  }

  @JvmStatic
  fun assertChecked(text: String) {
    withText(text).assertAny(isChecked())
  }

  @JvmStatic
  fun assertUnchecked(resId: Int) {
    resId.resourceMatcher().assertAny(not(isChecked()))
  }

  @JvmStatic
  fun assertUnchecked(text: String) {
    withText(text).assertAny(not(isChecked()))
  }
}