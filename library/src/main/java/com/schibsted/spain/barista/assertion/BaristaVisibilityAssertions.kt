package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.AmbiguousViewMatcherException
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import com.schibsted.spain.barista.internal.matcher.HelperMatchers
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsNot.not

object BaristaVisibilityAssertions {

    @JvmStatic
    fun assertDisplayed(resId: Int) {
        assertDisplayed(resId.resourceMatcher())
    }

    @JvmStatic
    fun assertDisplayed(text: String) {
        assertDisplayed(withText(text))
    }

    @JvmStatic
    fun assertDisplayed(@IdRes resId: Int, text: String) {
        onView(withId(resId)).check(matches(withText(text)))
    }

    private fun assertDisplayed(matcher: Matcher<View>) {
        val spyFailureHandler = SpyFailureHandler()
        try {
            onView(matcher)
                    .withFailureHandler(spyFailureHandler)
                    .check(matches(isDisplayed()))
        } catch (multipleViews: AmbiguousViewMatcherException) {
            try {
                onView(HelperMatchers.firstViewOf(allOf(matcher, isDisplayed())))
                        .withFailureHandler(spyFailureHandler)
                        .check(matches(isDisplayed()))
            } catch (notDisplayedError: NoMatchingViewException) {
                spyFailureHandler.resendFirstError("View ${matcher.description()} wasn't displayed on the screen")
            }
        }
    }

    @JvmStatic
    fun assertNotExist(resId: Int) {
        onView(resId.resourceMatcher()).check(doesNotExist())
    }

    @JvmStatic
    fun assertNotExist(text: String) {
        onView(withText(text)).check(doesNotExist())
    }

    @JvmStatic
    fun assertNotDisplayed(resId: Int) {
        onView(resId.resourceMatcher()).check(matches(not(isDisplayed())))
    }

    @JvmStatic
    fun assertNotDisplayed(text: String) {
        onView(withText(text)).check(matches(not(isDisplayed())))
    }

    @JvmStatic
    fun assertNotDisplayed(@IdRes resId: Int, text: String) {
        onView(withId(resId)).check(matches(not(withText(text))))
    }

    @JvmStatic
    fun assertContains(text: String) {
        assertDisplayed(withText(containsString(text)))
    }

    @JvmStatic
    fun assertContains(@IdRes resId: Int, text: String) {
        onView(withId(resId)).check(matches(withText(containsString(text))))
    }

    @JvmStatic
    fun assertNotContains(text: String) {
        onView(withText(containsString(text))).check(doesNotExist())
    }

    @JvmStatic
    fun assertNotContains(@IdRes resId: Int, text: String) {
        onView(allOf(withId(resId), withText(containsString(text)))).check(doesNotExist())
    }
}