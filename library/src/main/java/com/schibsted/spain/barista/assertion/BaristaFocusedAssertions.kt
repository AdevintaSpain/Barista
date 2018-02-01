package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.matcher.ViewMatchers.hasFocus
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.magicAssert
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.Matchers.not

object BaristaFocusedAssertions {

    @JvmStatic
    fun assertFocused(resId: Int) {
        resId.resourceMatcher().magicAssert(hasFocus())
    }

    @JvmStatic
    fun assertNotFocused(@IdRes resId: Int) {
        resId.resourceMatcher().magicAssert(not(hasFocus()))
    }

    @JvmStatic
    fun assertFocused(text: String) {
        withText(text).magicAssert(hasFocus())
    }

    @JvmStatic
    fun assertNotFocused(text: String) {
        withText(text).magicAssert(not(hasFocus()))
    }
}