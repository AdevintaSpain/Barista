package com.adevinta.android.barista.assertion

import androidx.test.espresso.matcher.ViewMatchers.isChecked
import com.adevinta.android.barista.internal.assertAny
import com.adevinta.android.barista.internal.util.resourceMatcher
import com.schibsted.spain.barista.internal.matcher.withCompatText
import org.hamcrest.Matchers.not

object BaristaCheckedAssertions {

  @JvmStatic
  fun assertChecked(resId: Int) {
    resId.resourceMatcher().assertAny(isChecked())
  }

  @JvmStatic
  fun assertChecked(text: String) {
    withCompatText(text).assertAny(isChecked())
  }

  @JvmStatic
  fun assertUnchecked(resId: Int) {
    resId.resourceMatcher().assertAny(not(isChecked()))
  }

  @JvmStatic
  fun assertUnchecked(text: String) {
    withCompatText(text).assertAny(not(isChecked()))
  }
}