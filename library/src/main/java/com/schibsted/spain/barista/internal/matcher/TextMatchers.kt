package com.schibsted.spain.barista.internal.matcher

import android.support.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matcher

object TextMatchers {

    @JvmStatic
    fun withContainsText(text: String): Matcher<View> = withText(containsString(text))
}