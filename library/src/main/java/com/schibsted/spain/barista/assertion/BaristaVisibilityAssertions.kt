@file:JvmName("BaristaVisibilityAssertions")

package com.schibsted.spain.barista.assertion

import android.support.annotation.ColorRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.matcher.TextColorMatcher
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

fun assertDisplayed(viewId: Int) {
  viewId.resourceMatcher().assertAny(isDisplayed())
}

fun assertDisplayed(text: String) {
  withText(text).assertAny(isDisplayed())
}

fun assertDisplayed(@IdRes viewId: Int, text: String) {
  viewId.resourceMatcher().assertAny(withText(text))
}

fun assertDisplayed(@IdRes viewId: Int, @StringRes stringId: Int) {
  viewId.resourceMatcher().assertAny(withText(stringId))
}

fun assertNotExist(resId: Int) {
  onView(resId.resourceMatcher()).check(doesNotExist())
}

fun assertNotExist(text: String) {
  onView(withText(text)).check(doesNotExist())
}

fun assertNotDisplayed(viewId: Int) {
  viewId.resourceMatcher().assertAny(not(isDisplayed()))
}

fun assertNotDisplayed(text: String) {
  withText(text).assertAny(not(isDisplayed()))
}

fun assertNotDisplayed(@IdRes viewId: Int, text: String) {
  viewId.resourceMatcher().assertAny(not(withText(text)))
}

fun assertContains(text: String) {
  withText(containsString(text)).assertAny(isDisplayed())
}

fun assertContains(@IdRes viewId: Int, text: String) {
  viewId.resourceMatcher().assertAny(withText(containsString(text)))
}

fun assertNotContains(text: String) {
  onView(withText(containsString(text))).check(doesNotExist())
}

fun assertNotContains(@IdRes resId: Int, text: String) {
  onView(allOf(withId(resId), withText(containsString(text)))).check(doesNotExist())
}

fun assertTextColorIs(@IdRes viewId: Int, @ColorRes colorRes: Int) {
  viewId.resourceMatcher().assertAny(TextColorMatcher(colorRes))
}

fun assertTextColorIsNot(@IdRes viewId: Int, @ColorRes colorRes: Int) {
  viewId.resourceMatcher().assertAny(not(TextColorMatcher(colorRes)))
}
