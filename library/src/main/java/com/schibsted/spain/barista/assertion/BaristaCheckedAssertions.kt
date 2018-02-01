package com.schibsted.spain.barista.assertion

import android.support.test.espresso.matcher.ViewMatchers.isChecked
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.schibsted.spain.barista.internal.magicAssert
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.Matchers.not

object BaristaCheckedAssertions {

    @JvmStatic
    fun assertChecked(resId: Int) {
        resId.resourceMatcher().magicAssert(isChecked())
    }

    @JvmStatic
    fun assertChecked(text: String) {
        withText(text).magicAssert(isChecked())
    }

    @JvmStatic
    fun assertUnchecked(resId: Int) {
        resId.resourceMatcher().magicAssert(not(isChecked()))
    }

    @JvmStatic
    fun assertUnchecked(text: String) {
        withText(text).magicAssert(not(isChecked()))
    }
}