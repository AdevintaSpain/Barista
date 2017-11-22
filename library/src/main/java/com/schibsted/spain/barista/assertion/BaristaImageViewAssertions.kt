package com.schibsted.spain.barista.assertion

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.internal.matcher.DrawableMatchers

object BaristaImageViewAssertions {

    @JvmStatic
    fun assertHasDrawable(@IdRes id: Int, @DrawableRes drawable: Int) {
        onView(withId(id)).check(matches(DrawableMatchers.withDrawable(drawable)))
    }

    @JvmStatic
    fun assertHasAnyDrawable(@IdRes id: Int) {
        onView(withId(id)).check(matches(DrawableMatchers.withAnyDrawable()))
    }

    @JvmStatic
    fun assertHasNoDrawable(@IdRes id: Int) {
        onView(withId(id)).check(matches(DrawableMatchers.withoutDrawable()))
    }
}