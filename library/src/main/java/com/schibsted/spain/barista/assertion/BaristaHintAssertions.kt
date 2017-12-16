package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.matcher.ViewMatchers.withHint
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.internal.magicAssert

object BaristaHintAssertions {

    @JvmStatic
    fun assertHint(@IdRes viewId: Int, @StringRes text: Int) {
        withId(viewId) magicAssert withHint(text)
    }

    @JvmStatic
    fun assertHint(@IdRes viewId: Int, text: String) {
        withId(viewId) magicAssert withHint(text)
    }

}