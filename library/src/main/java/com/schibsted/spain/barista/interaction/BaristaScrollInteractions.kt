package com.schibsted.spain.barista.interaction

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.PerformException
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import com.schibsted.spain.barista.internal.util.resourceMatcher
import com.schibsted.spain.barista.internal.viewaction.NestedEnabledScrollToAction.nestedScrollToAction
import org.hamcrest.Matcher

/**
 * Scrolls in Espresso are not as great as we could except. For that reason, we will try
 * to scroll several times instead of just one.
 */
object BaristaScrollInteractions {

    // This value has been mathematically calculated and proven to be precisely the exact number of retries needed to always work.
    // Not really, we just tried hundreds of times with different values and this seems to be the best one.
    private val MAX_SCROLL_ATTEMPTS = 50

    @JvmStatic
    fun scrollTo(resId: Int) {
        scrollWithMultipleAttempts(resId.resourceMatcher(), failAtEnd = true)
    }

    @JvmStatic
    fun scrollTo(text: String) {
        scrollWithMultipleAttempts(withText(text), failAtEnd = true)
    }

    @JvmStatic
    fun safelyScrollTo(id: Int) {
        scrollWithMultipleAttempts(id.resourceMatcher(), failAtEnd = false)
    }

    @JvmStatic
    fun safelyScrollTo(text: String) {
        scrollWithMultipleAttempts(withText(text), failAtEnd = false)
    }

    private fun scrollWithMultipleAttempts(matcher: Matcher<View>, failAtEnd: Boolean) {
        val spyFailureHandler = SpyFailureHandler()
        for (i in 1..MAX_SCROLL_ATTEMPTS) {
            try {
                onView(matcher)
                        .withFailureHandler(spyFailureHandler)
                        .perform(nestedScrollToAction())
            } catch (exception: PerformException) {
                if (i == MAX_SCROLL_ATTEMPTS && failAtEnd) {
                    spyFailureHandler.resendLastError(
                            "Could not scroll to ${matcher.description()}. Retried ${MAX_SCROLL_ATTEMPTS} times but all failed")
                }
            }
        }
    }
}
