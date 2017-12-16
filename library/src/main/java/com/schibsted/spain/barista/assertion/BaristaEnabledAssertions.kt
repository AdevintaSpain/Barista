package com.schibsted.spain.barista.assertion

import android.support.test.espresso.matcher.ViewMatchers.isEnabled
import com.schibsted.spain.barista.internal.magicAssert
import com.schibsted.spain.barista.internal.not

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
        resId magicAssert !isEnabled()
    }

    @JvmStatic
    fun assertDisabled(text: String) {
        text magicAssert !isEnabled()
    }
}