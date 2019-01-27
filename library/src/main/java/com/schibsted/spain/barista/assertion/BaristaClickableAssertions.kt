package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.Matchers.not

object BaristaClickableAssertions {

  @JvmStatic
  fun assertClickable(@IdRes resId: Int) {
    resId.resourceMatcher().assertAny(isClickable())
  }

  @JvmStatic
  fun assertClickable(text: String) {
    withText(text).assertAny(isClickable())
  }

  @JvmStatic
  fun assertNotClickable(@IdRes resId: Int) {
    resId.resourceMatcher().assertAny(not(isClickable()))
  }

  @JvmStatic
  fun assertNotClickable(text: String) {
    withText(text).assertAny(not(isClickable()))
  }
}