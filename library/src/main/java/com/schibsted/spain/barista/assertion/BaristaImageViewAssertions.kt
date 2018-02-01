package com.schibsted.spain.barista.assertion

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.internal.magicAssert
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withAnyDrawable
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withoutDrawable

object BaristaImageViewAssertions {

    @JvmStatic
    fun assertHasDrawable(@IdRes imageViewId: Int, @DrawableRes drawable: Int) {
        withId(imageViewId).magicAssert(withDrawable(drawable))
    }

    @JvmStatic
    fun assertHasAnyDrawable(@IdRes imageViewId: Int) {
        withId(imageViewId).magicAssert(withAnyDrawable())
    }

    @JvmStatic
    fun assertHasNoDrawable(@IdRes imageViewId: Int) {
        withId(imageViewId).magicAssert(withoutDrawable())
    }
}