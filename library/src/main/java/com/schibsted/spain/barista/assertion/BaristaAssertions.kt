package com.schibsted.spain.barista.assertion

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.AmbiguousViewMatcherException
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoActivityResumedException
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import com.schibsted.spain.barista.internal.failurehandler.RethrowingFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedWithId
import com.schibsted.spain.barista.internal.matcher.DrawableMatchers.withDrawable
import com.schibsted.spain.barista.internal.matcher.HelperMatchers.firstViewOf
import com.schibsted.spain.barista.internal.matcher.RecyclerViewItemCountAssertion
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsNot.not
import org.junit.Assert.fail

object BaristaAssertions {

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
                onView(firstViewOf(allOf(matcher, isDisplayed())))
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
    fun assertEnabled(resId: Int) {
        onView(resId.resourceMatcher()).check(matches(isEnabled()))
    }

    @JvmStatic
    fun assertEnabled(text: String) {
        onView(withText(text)).check(matches(isEnabled()))
    }

    @JvmStatic
    fun assertDisabled(resId: Int) {
        onView(resId.resourceMatcher()).check(matches(not(isEnabled())))
    }

    @JvmStatic
    fun assertDisabled(text: String) {
        onView(withText(text)).check(matches(not(isEnabled())))
    }

    @JvmStatic
    fun assertChecked(resId: Int) {
        onView(resId.resourceMatcher()).check(matches(isChecked()))
    }

    @JvmStatic
    fun assertChecked(text: String) {
        onView(withText(text)).check(matches(isChecked()))
    }

    @JvmStatic
    fun assertUnchecked(resId: Int) {
        onView(resId.resourceMatcher()).check(matches(isNotChecked()))
    }

    @JvmStatic
    fun assertUnchecked(text: String) {
        onView(withText(text)).check(matches(isNotChecked()))
    }

    @JvmStatic
    fun assertThatBackButtonClosesTheApp() {
        // Will launch an Exception if it closes the app
        try {
            onView(isRoot())
                    .withFailureHandler(RethrowingFailureHandler())
                    .perform(ViewActions.pressBack())
            // One of our Activities is appearing on the screen :(
            fail("The back button didn't close the app")
        } catch (noActivityException: NoActivityResumedException) {
            //Yey!
        }
    }

    @JvmStatic
    fun assertRecyclerViewItemCount(@IdRes recyclerViewId: Int, expectedItemCount: Int) {
        onView(displayedWithId(recyclerViewId)).check(
                RecyclerViewItemCountAssertion(expectedItemCount))
    }

    @JvmStatic
    fun assertHint(@IdRes id: Int, @StringRes text: Int) {
        onView(withId(id)).check(matches(withHint(text)))
    }

    @JvmStatic
    fun assertHint(@IdRes id: Int, text: String) {
        onView(withId(id)).check(matches(withHint(text)))
    }

    @JvmStatic
    fun assertDrawable(@IdRes id: Int, @DrawableRes drawable: Int) {
        onView(withId(id)).check(matches(withDrawable(drawable)))
    }

    @JvmStatic
    fun assertFocused(@IdRes resId: Int) {
        onView(resId.resourceMatcher()).check(matches(hasFocus()))
    }

    @JvmStatic
    fun assertNotFocused(@IdRes resId: Int) {
        onView(resId.resourceMatcher()).check(matches(not(hasFocus())))
    }

    @JvmStatic
    fun assertFocused(text: String) {
        onView(withText(text)).check(matches(hasFocus()))
    }

    @JvmStatic
    fun assertNotFocused(text: String) {
        onView(withText(text)).check(matches(not(hasFocus())))
    }
}
