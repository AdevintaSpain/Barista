package com.schibsted.spain.barista.assertion

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import com.schibsted.spain.barista.internal.magicAssert
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher.Companion.withAnyBackground
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher.Companion.withBackground
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher.Companion.withoutBackground
import com.schibsted.spain.barista.internal.util.resourceMatcher

object BaristaBackgroundAssertions {

    @JvmStatic
    fun assertHasBackground(@IdRes viewId: Int, @DrawableRes drawable: Int) {
        viewId.resourceMatcher().magicAssert(withBackground(drawable))
    }

    @JvmStatic
    fun assertHasAnyBackground(@IdRes viewId: Int) {
        viewId.resourceMatcher().magicAssert(withAnyBackground())
    }

    @JvmStatic
    fun assertHasNoBackground(@IdRes viewId: Int) {
        viewId.resourceMatcher().magicAssert(withoutBackground())
    }
}