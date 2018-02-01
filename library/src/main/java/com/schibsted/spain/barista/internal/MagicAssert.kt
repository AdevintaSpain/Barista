package com.schibsted.spain.barista.internal

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.view.View
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import com.schibsted.spain.barista.internal.matcher.HelperMatchers.firstViewOf
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

/**
 * Extension function alias for [magicAssertOnView]
 */
fun Matcher<View>.magicAssert(condition: Matcher<View>) {
    magicAssertOnView(viewMatcher = this, condition = condition)
}

/**
 * Performs an assertion of a [condition] on a view described by [viewMatcher].
 *
 * Attempts to assert using multiple scenarios for the [viewMatcher]:
 * 1. Just one view matches the [viewMatcher].
 * 2. Multiple views match the [viewMatcher]: will pass if at least one of them matches the [condition].
 */
fun magicAssertOnView(viewMatcher: Matcher<View>, condition: Matcher<View>) {
    val spyFailureHandler = SpyFailureHandler()
    try {
        tryToAssert(viewMatcher, condition, spyFailureHandler)
    } catch (firstError: RuntimeException) {
        try {
            tryToAssertFirstView(viewMatcher, condition, spyFailureHandler)
        } catch (secondError: RuntimeException) {
            spyFailureHandler.resendFirstError("View ${viewMatcher.description()} wasn't displayed on the screen")
        }
    }
}

private fun tryToAssertFirstView(viewMatcher: Matcher<View>, condition: Matcher<View>, spyFailureHandler: SpyFailureHandler) {
    onView(firstViewOf(allOf(viewMatcher, condition)))
            .withFailureHandler(spyFailureHandler)
            .check(ViewAssertions.matches(condition))
}

private fun tryToAssert(viewMatcher: Matcher<View>, condition: Matcher<View>, spyFailureHandler: SpyFailureHandler) {
    onView(viewMatcher)
            .withFailureHandler(spyFailureHandler)
            .check(ViewAssertions.matches(condition))
}
