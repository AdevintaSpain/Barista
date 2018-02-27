package com.schibsted.spain.barista.assertion

import android.support.annotation.ColorRes
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import com.schibsted.spain.barista.internal.matcher.HelperMatchers
import com.schibsted.spain.barista.internal.matcher.TextColorMatcher
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




    /**
     * Attempts to find the view with multiple conditions:
     * 1. Simplest case
     * 2. More than one view
     */
    private fun assertVisibility(viewMatcher: Matcher<View>, viewAssertion: ViewAssertion, errorString: String) {
        val spyFailureHandler = SpyFailureHandler()
        try {
            onView(viewMatcher)
                    .withFailureHandler(spyFailureHandler)
                    .check(viewAssertion)
        } catch (firstError: RuntimeException) {
            try {
                onView(HelperMatchers.firstViewOf(allOf(viewMatcher)))
                        .withFailureHandler(spyFailureHandler)
                        .check(viewAssertion)
            } catch (secondError: RuntimeException) {
                spyFailureHandler.resendFirstError("View ${viewMatcher.description()} $errorString")
            }
        }
    }

    @JvmStatic
    fun assertDisplayed(viewMatcher: Matcher<View>) {
        assertVisibility(viewMatcher, matches(isDisplayed()),"is NOT Displayed on the screen")
    }

    @JvmStatic
    fun assertNotDisplayed(viewMatcher: Matcher<View>) {
        assertVisibility(viewMatcher, matches(not(isDisplayed())), "is Displayed on the screen")
    }

    @JvmStatic
    fun assertEffectivelyVisible(viewMatcher: Matcher<View>) {
        assertVisibility(viewMatcher, matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)),"is not Effectively visible on the screen")
    }

    @JvmStatic
    fun assertNotExist(viewMatcher: Matcher<View>) {
        assertVisibility(viewMatcher, doesNotExist(),"is present in the hierarchy")
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

    @JvmStatic
    fun assertTextColorIs(@IdRes resId: Int, @ColorRes colorRes: Int) {
        onView(withId(resId)).check(matches(TextColorMatcher(colorRes)))
    }

    @JvmStatic
    fun assertTextColorIsNot(@IdRes resId: Int, @ColorRes colorRes: Int) {
        onView(withId(resId)).check(matches(not(TextColorMatcher(colorRes))))
    }
}