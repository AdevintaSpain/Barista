package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withHint
import android.support.test.espresso.matcher.ViewMatchers.withId

object BaristaHintAssertions {

    @JvmStatic
    fun assertHint(@IdRes id: Int, @StringRes text: Int) {
        onView(withId(id)).check(matches(withHint(text)))
    }

    @JvmStatic
    fun assertHint(@IdRes id: Int, text: String) {
        onView(withId(id)).check(matches(withHint(text)))
    }

}