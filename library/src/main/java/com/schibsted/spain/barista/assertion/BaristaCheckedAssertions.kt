package com.schibsted.spain.barista.assertion

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.schibsted.spain.barista.internal.util.resourceMatcher

object BaristaCheckedAssertions {

    @JvmStatic
    fun assertChecked(resId: Int) {
        onView(resId.resourceMatcher()).check(matches(isChecked()))
    }

    @JvmStatic
    fun assertChecked(text: String) {
        onView(withText(text)).check(matches(isChecked()))
    }

    @JvmStatic
    fun assertUnchecked(resId: Int) {
        onView(resId.resourceMatcher()).check(matches(isNotChecked()))
    }

    @JvmStatic
    fun assertUnchecked(text: String) {
        onView(withText(text)).check(matches(isNotChecked()))
    }
}