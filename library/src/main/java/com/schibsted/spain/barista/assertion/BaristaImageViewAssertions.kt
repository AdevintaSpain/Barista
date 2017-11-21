package com.schibsted.spain.barista.assertion

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import com.schibsted.spain.barista.internal.matcher.DrawableMatchers

object BaristaImageViewAssertions {

    @JvmStatic
    fun assertDrawable(@IdRes id: Int, @DrawableRes drawable: Int) {
        Espresso.onView(ViewMatchers.withId(id)).check(ViewAssertions.matches(DrawableMatchers.withDrawable(drawable)))
    }

}