package com.schibsted.spain.barista.assertion

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isEnabled
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.core.IsNot.not

object BaristaEnabledAssertions {

    @JvmStatic
    fun assertEnabled(resId: Int) {
        onView(resId.resourceMatcher()).check(matches(isEnabled()))
    }

    @JvmStatic
    fun assertEnabled(text: String) {
        onView(withText(text)).check(matches(isEnabled()))
    }

    @JvmStatic
    fun assertDisabled(resId: Int) {
        onView(resId.resourceMatcher()).check(matches(not(isEnabled())))
    }

    @JvmStatic
    fun assertDisabled(text: String) {
        onView(withText(text)).check(matches(not(isEnabled())))
    }
}