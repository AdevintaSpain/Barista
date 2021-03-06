package com.adevinta.android.barista.assertion

import androidx.annotation.IdRes
import androidx.test.espresso.matcher.ViewMatchers.hasFocus
import com.adevinta.android.barista.internal.assertAny
import com.adevinta.android.barista.internal.util.resourceMatcher
import com.adevinta.android.barista.internal.matcher.withCompatText
import org.hamcrest.Matchers.not

object BaristaFocusedAssertions {

  @JvmStatic
  fun assertFocused(resId: Int) {
    resId.resourceMatcher().assertAny(hasFocus())
  }

  @JvmStatic
  fun assertNotFocused(@IdRes resId: Int) {
    resId.resourceMatcher().assertAny(not(hasFocus()))
  }

  @JvmStatic
  fun assertFocused(text: String) {
    withCompatText(text).assertAny(hasFocus())
  }

  @JvmStatic
  fun assertNotFocused(text: String) {
    withCompatText(text).assertAny(not(hasFocus()))
  }
}