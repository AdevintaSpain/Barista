package com.schibsted.spain.barista

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.PerformException
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import com.schibsted.spain.barista.custom.NestedEnabledScrollToAction.nestedScrollToAction
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import org.hamcrest.Matcher

/**
 * Scrolls in Espresso are not as great as we could except. For that reason, we will try
 * to scroll several times instead of just one. If any of them worked, we will share the
 * Espresso's Exception to the caller.
 */
object BaristaScrollActions {

    private val MAX_SCROLL_ATTEMPTS = 5

    @JvmStatic
    fun scrollTo(@IdRes id: Int) {
        scrollWithMultipleAttempts(withId(id), failAtEnd = true)
    }

    @JvmStatic
    fun scrollTo(text: String) {
        scrollWithMultipleAttempts(withText(text), failAtEnd = true)
    }

    @JvmStatic
    fun safelyScrollTo(@IdRes id: Int) {
        scrollWithMultipleAttempts(withId(id), failAtEnd = false)
    }

    @JvmStatic
    fun safelyScrollTo(text: String) {
        scrollWithMultipleAttempts(withText(text), failAtEnd = false)
    }

    private fun scrollWithMultipleAttempts(matcher: Matcher<View>, failAtEnd: Boolean) {
        val spyFailureHandler = SpyFailureHandler()
        for (i in 0..MAX_SCROLL_ATTEMPTS) {
            try {
                onView(matcher)
                        .withFailureHandler(spyFailureHandler)
                        .perform(nestedScrollToAction())
            } catch (exception: PerformException) {
                if (i == MAX_SCROLL_ATTEMPTS && failAtEnd) {
                    spyFailureHandler.resendLastError("Could not scroll to ${matcher.description()}. Retried ${MAX_SCROLL_ATTEMPTS} times but all failed")
                }
            }
        }
    }
}
