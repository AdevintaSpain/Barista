package com.adevinta.android.barista.assertion

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.annotation.StyleableRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.adevinta.android.barista.internal.assertAny
import com.adevinta.android.barista.internal.matcher.TextColorMatcher
import com.adevinta.android.barista.internal.matcher.TextStyleableColorMatcher
import com.adevinta.android.barista.internal.util.resourceMatcher
import com.adevinta.android.barista.internal.matcher.withCompatText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
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
    withCompatText(text).assertAny(isDisplayed())
  }

  @JvmStatic
  fun assertDisplayed(@IdRes viewId: Int, text: String) {
    viewId.resourceMatcher().assertAny(allOf(isDisplayed(), withCompatText(text)))
  }

  @JvmStatic
  fun assertDisplayed(@IdRes viewId: Int, @StringRes stringId: Int) {
    viewId.resourceMatcher().assertAny(allOf(isDisplayed(), withText(stringId)))
  }

  @JvmStatic
  fun assertNotExist(resId: Int) {
    onView(resId.resourceMatcher()).check(doesNotExist())
  }

  @JvmStatic
  fun assertNotExist(text: String) {
    onView(withCompatText(text)).check(doesNotExist())
  }

  @JvmStatic
  fun assertNotDisplayed(viewId: Int) {
    viewId.resourceMatcher().assertAny(not(isDisplayed()))
  }

  @JvmStatic
  fun assertNotDisplayed(text: String) {
    withCompatText(text).assertAny(not(isDisplayed()))
  }

  @JvmStatic
  fun assertNotDisplayed(viewMatcher: Matcher<View>) {
    viewMatcher.assertAny(not(isDisplayed()))
  }

  @JvmStatic
  fun assertNotDisplayed(@IdRes viewId: Int, text: String) {
    viewId.resourceMatcher().assertAny(not(allOf(isDisplayed(), withCompatText(text))))
  }

  @JvmStatic
  fun assertNotDisplayed(@IdRes viewId: Int, @StringRes stringId: Int) {
    viewId.resourceMatcher().assertAny(not(allOf(isDisplayed(), withText(stringId))))
  }

  @JvmStatic
  fun assertContains(text: String) {
    withCompatText(containsString(text)).assertAny(isDisplayed())
  }

  @JvmStatic
  fun assertContains(@StringRes stringId: Int) {
    val resourceText = InstrumentationRegistry.getInstrumentation().targetContext.getString(stringId)
    assertContains(resourceText)
  }

  @JvmStatic
  fun assertContains(@IdRes viewId: Int, text: String) {
    viewId.resourceMatcher().assertAny(withCompatText(containsString(text)))
  }

  @JvmStatic
  fun assertContains(@IdRes viewId: Int, @StringRes stringId: Int) {
    val resourceText = InstrumentationRegistry.getInstrumentation().targetContext.getString(stringId)
    assertContains(viewId, resourceText)
  }

  @JvmStatic
  fun assertNotContains(text: String) {
    onView(withCompatText(containsString(text))).check(doesNotExist())
  }

  @JvmStatic
  fun assertNotContains(@StringRes stringId: Int) {
    val resourceText = InstrumentationRegistry.getInstrumentation().targetContext.getString(stringId)
    assertNotContains(resourceText)
  }

  @JvmStatic
  fun assertNotContains(@IdRes resId: Int, text: String) {
    onView(allOf(withId(resId), withCompatText(containsString(text)))).check(doesNotExist())
  }

  @JvmStatic
  fun assertNotContains(@IdRes resId: Int, @StringRes stringId: Int) {
    val resourceText = InstrumentationRegistry.getInstrumentation().targetContext.getString(stringId)
    assertNotContains(resId, resourceText)
  }

  @JvmStatic
  fun assertTextColorIs(@IdRes viewId: Int, color: Int) {
    viewId.resourceMatcher().assertAny(TextColorMatcher(color))
  }

  @JvmStatic
  fun assertTextColorIsNot(@IdRes viewId: Int, color: Int) {
    viewId.resourceMatcher().assertAny(not(TextColorMatcher(color)))
  }

  @JvmStatic
  fun assertTextColorIs(
      @IdRes viewId: Int,
      @StyleableRes styleableRes: IntArray,
      @StyleRes styleRes: Int,
      @StyleableRes attrColor: Int
  ) {
    viewId.resourceMatcher().assertAny(TextStyleableColorMatcher(
        styleableRes, styleRes, attrColor
    ))
  }

  @JvmStatic
  fun assertTextColorIsNot(
      @IdRes viewId: Int,
      @StyleableRes styleableRes: IntArray,
      @StyleRes styleRes: Int,
      @StyleableRes attrColor: Int
  ) {
    viewId.resourceMatcher().assertAny(not(
        TextStyleableColorMatcher(
            styleableRes, styleRes, attrColor
        )
    ))
  }
}
