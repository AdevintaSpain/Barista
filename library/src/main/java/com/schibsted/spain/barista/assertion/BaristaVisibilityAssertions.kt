package com.schibsted.spain.barista.assertion

import android.support.annotation.ColorRes
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.matcher.ViewMatchers.*
import com.schibsted.spain.barista.internal.magicAssert
import com.schibsted.spain.barista.internal.matcher.TextColorMatcher
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

object BaristaVisibilityAssertions {

    @JvmStatic
    fun assertDisplayed(viewId: Int) {
        viewId magicAssert isDisplayed()
    }

    @JvmStatic
    fun assertDisplayed(text: String) {
        text magicAssert isDisplayed()
    }

    @JvmStatic
    fun assertDisplayed(@IdRes viewId: Int, text: String) {
        viewId magicAssert withText(text)
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
        viewId magicAssert not(isDisplayed())
    }

    @JvmStatic
    fun assertNotDisplayed(text: String) {
        text magicAssert not(isDisplayed())
    }

    @JvmStatic
    fun assertNotDisplayed(@IdRes viewId: Int, text: String) {
        viewId magicAssert not(withText(text))
    }

    @JvmStatic
    fun assertContains(text: String) {
        withText(containsString(text)) magicAssert isDisplayed()
    }

    @JvmStatic
    fun assertContains(@IdRes viewId: Int, text: String) {
        viewId magicAssert withText(containsString(text))
    }

    @JvmStatic
    fun assertNotContains(text: String) {
        onView(withText(containsString(text))).check(doesNotExist())
    }

    @JvmStatic
    fun assertNotContains(@IdRes resId: Int, text: String) {
        onView(allOf(withId(resId), withText(containsString(text)))).check(doesNotExist())
    }

    @JvmStatic
    fun assertTextColorIs(@IdRes viewId: Int, @ColorRes colorRes: Int) {
        viewId magicAssert TextColorMatcher(colorRes)
    }

    @JvmStatic
    fun assertTextColorIsNot(@IdRes viewId: Int, @ColorRes colorRes: Int) {
        viewId magicAssert not(TextColorMatcher(colorRes))
    }
}
