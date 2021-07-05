package com.adevinta.android.barista.assertion

import androidx.test.espresso.matcher.ViewMatchers.isClickable
import com.adevinta.android.barista.internal.assertAny
import com.adevinta.android.barista.internal.util.resourceMatcher
import com.adevinta.android.barista.internal.matcher.withCompatText
import org.hamcrest.Matchers.not

object BaristaClickableAssertions {

  @JvmStatic
  fun assertClickable(resId: Int) {
    resId.resourceMatcher().assertAny(isClickable())
  }

  @JvmStatic
  fun assertClickable(text: String) {
    withCompatText(text).assertAny(isClickable())
  }

  @JvmStatic
  fun assertNotClickable(resId: Int) {
    resId.resourceMatcher().assertAny(not(isClickable()))
  }

  @JvmStatic
  fun assertNotClickable(text: String) {
    withCompatText(text).assertAny(not(isClickable()))
  }
}