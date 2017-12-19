package com.schibsted.spain.barista.assertion

import android.support.test.espresso.matcher.ViewMatchers.isEnabled
import com.schibsted.spain.barista.internal.magicAssert
import org.hamcrest.Matchers.not

object BaristaEnabledAssertions {

    @JvmStatic
    fun assertEnabled(resId: Int) {
        resId magicAssert isEnabled()
    }

    @JvmStatic
    fun assertEnabled(text: String) {
        text magicAssert isEnabled()
    }

    @JvmStatic
    fun assertDisabled(resId: Int) {
        resId magicAssert not(isEnabled())
    }

    @JvmStatic
    fun assertDisabled(text: String) {
        text magicAssert not(isEnabled())
    }
}