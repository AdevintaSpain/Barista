package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.matcher.ViewMatchers.hasFocus
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.Matchers.not

object BaristaFocusedAssertions {

    @JvmStatic
    fun assertFocused(resId: Int) {
        resId.resourceMatcher().assertAny(hasFocus())
    }

    @JvmStatic
    fun assertNotFocused(@IdRes resId: Int) {
        resId.resourceMatcher().assertAny(not(hasFocus()))
    }

    @JvmStatic
    fun assertFocused(text: String) {
        withText(text).assertAny(hasFocus())
    }

    @JvmStatic
    fun assertNotFocused(text: String) {
        withText(text).assertAny(not(hasFocus()))
    }
}