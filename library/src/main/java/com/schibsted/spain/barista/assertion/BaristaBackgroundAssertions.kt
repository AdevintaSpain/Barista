package com.schibsted.spain.barista.assertion

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import com.schibsted.spain.barista.internal.magicAssert
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher.Companion.withAnyBackground
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher.Companion.withBackground
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher.Companion.withoutBackground

object BaristaBackgroundAssertions {

    @JvmStatic
    fun assertHasBackground(@IdRes viewId: Int, @DrawableRes drawable: Int) {
        viewId magicAssert withBackground(drawable)
    }

    @JvmStatic
    fun assertHasAnyBackground(@IdRes viewId: Int) {
        viewId magicAssert withAnyBackground()
    }

    @JvmStatic
    fun assertHasNoBackground(@IdRes viewId: Int) {
        viewId magicAssert withoutBackground()
    }
}