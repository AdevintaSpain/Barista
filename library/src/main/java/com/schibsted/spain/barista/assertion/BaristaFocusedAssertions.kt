@file:JvmName("BaristaFocusedAssertions")

package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.matcher.ViewMatchers.hasFocus
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.Matchers.not

fun assertFocused(resId: Int) {
  resId.resourceMatcher().assertAny(hasFocus())
}

fun assertNotFocused(@IdRes resId: Int) {
  resId.resourceMatcher().assertAny(not(hasFocus()))
}

fun assertFocused(text: String) {
  withText(text).assertAny(hasFocus())
}

fun assertNotFocused(text: String) {
  withText(text).assertAny(not(hasFocus()))
}
