package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.hasFocus
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.core.IsNot.not

object BaristaFocusAssertions {

    @JvmStatic
    fun assertFocused(@IdRes resId: Int) {
        onView(resId.resourceMatcher()).check(matches(hasFocus()))
    }

    @JvmStatic
    fun assertNotFocused(@IdRes resId: Int) {
        onView(resId.resourceMatcher()).check(matches(not(hasFocus())))
    }

    @JvmStatic
    fun assertFocused(text: String) {
        onView(withText(text)).check(matches(hasFocus()))
    }

    @JvmStatic
    fun assertNotFocused(text: String) {
        onView(withText(text)).check(matches(not(hasFocus())))
    }
}