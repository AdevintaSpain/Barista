package com.schibsted.spain.barista.assertion

import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import androidx.test.platform.app.InstrumentationRegistry
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.matcher.TextColorMatcher
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

object BaristaVisibilityAssertions {

  @JvmStatic
  fun assertDisplayed(viewMatcher: Matcher<View>) {
    viewMatcher.assertAny(isDisplayed())
  }

  @JvmStatic
  fun assertDisplayed(viewId: Int) {
    viewId.resourceMatcher().assertAny(isDisplayed())
  }

  @JvmStatic
  fun assertDisplayed(text: String) {
    withText(text).assertAny(isDisplayed())
  }

  @JvmStatic
  fun assertDisplayed(@IdRes viewId: Int, text: String) {
    viewId.resourceMatcher().assertAny(withText(text))
  }

  @JvmStatic
  fun assertDisplayed(@IdRes viewId: Int, @StringRes stringId: Int) {
    viewId.resourceMatcher().assertAny(withText(stringId))
  }

  @JvmStatic
  fun assertNotExist(resId: Int) {
    onView(resId.resourceMatcher()).check(doesNotExist())
  }

  @JvmStatic
  fun assertNotExist(text: String) {
    onView(withText(text)).check(doesNotExist())
  }

  @JvmStatic
  fun assertNotDisplayed(viewId: Int) {
    viewId.resourceMatcher().assertAny(not(isDisplayed()))
  }

  @JvmStatic
  fun assertNotDisplayed(text: String) {
    withText(text).assertAny(not(isDisplayed()))
  }

  @JvmStatic
  fun assertNotDisplayed(viewMatcher: Matcher<View>) {
    viewMatcher.assertAny(not(isDisplayed()))
  }

  @JvmStatic
  fun assertNotDisplayed(@IdRes viewId: Int, text: String) {
    viewId.resourceMatcher().assertAny(not(withText(text)))
  }

  @JvmStatic
  fun assertNotDisplayed(@IdRes viewId: Int, @StringRes stringId: Int) {
    viewId.resourceMatcher().assertAny(not(withText(stringId)))
  }

  @JvmStatic
  fun assertContains(text: String) {
    withText(containsString(text)).assertAny(isDisplayed())
  }

  @JvmStatic
  fun assertContains(@StringRes stringId: Int) {
    val resourceText = InstrumentationRegistry.getInstrumentation().targetContext.getString(stringId)
    assertContains(resourceText)
  }

  @JvmStatic
  fun assertContains(@IdRes viewId: Int, text: String) {
    viewId.resourceMatcher().assertAny(withText(containsString(text)))
  }

  @JvmStatic
  fun assertContains(@IdRes viewId: Int, @StringRes stringId: Int) {
    val resourceText = InstrumentationRegistry.getInstrumentation().targetContext.getString(stringId)
    assertContains(viewId, resourceText)
  }

  @JvmStatic
  fun assertNotContains(text: String) {
    onView(withText(containsString(text))).check(doesNotExist())
  }

  @JvmStatic
  fun assertNotContains(@StringRes stringId: Int) {
    val resourceText = InstrumentationRegistry.getInstrumentation().targetContext.getString(stringId)
    assertNotContains(resourceText)
  }

  @JvmStatic
  fun assertNotContains(@IdRes resId: Int, text: String) {
    onView(allOf(withId(resId), withText(containsString(text)))).check(doesNotExist())
  }

  @JvmStatic
  fun assertNotContains(@IdRes resId: Int, @StringRes stringId: Int) {
    val resourceText = InstrumentationRegistry.getInstrumentation().targetContext.getString(stringId)
    assertNotContains(resId, resourceText)
  }

  @JvmStatic
  fun assertTextColorIs(@IdRes viewId: Int, @ColorRes colorRes: Int) {
    viewId.resourceMatcher().assertAny(TextColorMatcher(colorRes))
  }

  @JvmStatic
  fun assertTextColorIsNot(@IdRes viewId: Int, @ColorRes colorRes: Int) {
    viewId.resourceMatcher().assertAny(not(TextColorMatcher(colorRes)))
  }
}
