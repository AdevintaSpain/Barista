package com.schibsted.spain.barista.assertion

import android.support.test.espresso.matcher.ViewMatchers.isEnabled
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.magicAssert
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.Matchers.not

object BaristaEnabledAssertions {

    @JvmStatic
    fun assertEnabled(resId: Int) {
        resId.resourceMatcher().magicAssert(isEnabled())
    }

    @JvmStatic
    fun assertEnabled(text: String) {
        withText(text).magicAssert(isEnabled())
    }

    @JvmStatic
    fun assertDisabled(resId: Int) {
        resId.resourceMatcher().magicAssert(not(isEnabled()))
    }

    @JvmStatic
    fun assertDisabled(text: String) {
        withText(text).magicAssert(not(isEnabled()))
    }
}