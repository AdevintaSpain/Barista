package com.schibsted.spain.barista.assertion

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoActivityResumedException
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.schibsted.spain.barista.internal.failurehandler.RethrowingFailureHandler
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedWithId
import com.schibsted.spain.barista.internal.matcher.DrawableMatchers.withDrawable
import com.schibsted.spain.barista.internal.matcher.RecyclerViewItemCountAssertion
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.core.IsNot.not
import org.junit.Assert.fail

object BaristaAssertions {

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
