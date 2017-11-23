package com.schibsted.spain.barista.assertion

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher

object BaristaBackgroundAssertions {

    @JvmStatic
    fun assertHasBackground(@IdRes id: Int, @DrawableRes drawable: Int) {
        Espresso.onView(ViewMatchers.withId(id)).check(ViewAssertions.matches(BackgroundMatcher.withBackground(drawable)))
    }

    @JvmStatic
    fun assertHasAnyBackground(@IdRes id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).check(ViewAssertions.matches(BackgroundMatcher.withAnyBackground()))
    }

    @JvmStatic
    fun assertHasNoBackground(@IdRes id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).check(ViewAssertions.matches(BackgroundMatcher.withoutBackground()))
    }
}