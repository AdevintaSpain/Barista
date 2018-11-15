@file:JvmName("BaristaCheckedAssertions")

package com.schibsted.spain.barista.assertion

import android.support.test.espresso.matcher.ViewMatchers.isChecked
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.Matchers.not

fun assertChecked(resId: Int) {
  resId.resourceMatcher().assertAny(isChecked())
}

fun assertChecked(text: String) {
  withText(text).assertAny(isChecked())
}

fun assertUnchecked(resId: Int) {
  resId.resourceMatcher().assertAny(not(isChecked()))
}

fun assertUnchecked(text: String) {
  withText(text).assertAny(not(isChecked()))
}
