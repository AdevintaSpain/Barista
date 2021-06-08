package com.adevinta.android.barista.interaction

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.adevinta.android.barista.internal.failurehandler.SpyFailureHandler
import com.adevinta.android.barista.internal.failurehandler.description
import com.adevinta.android.barista.internal.util.resourceMatcher
import com.adevinta.android.barista.internal.viewaction.NestedEnabledScrollToAction.nestedScrollToAction
import org.hamcrest.Matcher

/**
 * Scrolls in Espresso are not as great as we could expect. For that reason, we will try
 * to scroll several times instead of just one.
 */
object BaristaScrollInteractions {

    // This value has been mathematically calculated and proven to be precisely the exact number of retries needed to always work.
    // Not really, we just tried hundreds of times with different values and this seems to be the best one.
    private val MAX_SCROLL_ATTEMPTS = 50

    @JvmStatic
    fun scrollTo(matcher: Matcher<View>) {
        scrollWithMultipleAttempts(matcher, failAtEnd = true)
    }

    @JvmStatic
    fun scrollTo(id: Int) {
        scrollWithMultipleAttempts(id.resourceMatcher(), failAtEnd = true)
    }

    @JvmStatic
    fun scrollTo(text: String) {
        scrollWithMultipleAttempts(withText(text), failAtEnd = true)
    }

    @JvmStatic
    fun safelyScrollTo(matcher: Matcher<View>) {
        scrollWithMultipleAttempts(matcher, failAtEnd = false)
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
                    spyFailureHandler
                            .resendLastError("Could not scroll to ${matcher.description()}. Retried ${MAX_SCROLL_ATTEMPTS} times but all failed")
                }
            }
        }
    }
}
