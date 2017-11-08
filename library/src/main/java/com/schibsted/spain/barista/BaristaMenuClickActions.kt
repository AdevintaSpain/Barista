package com.schibsted.spain.barista

import android.support.annotation.IdRes
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.RootMatchers.isPlatformPopup
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import com.schibsted.spain.barista.custom.DisplayedMatchers.displayedAnd
import com.schibsted.spain.barista.custom.HelperMatchers.menuIdMatcher
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.withFailureHandler
import org.hamcrest.Matcher
import org.hamcrest.Matchers.hasToString

object BaristaMenuClickActions {

    @JvmStatic
    fun clickMenu(@IdRes id: Int) {
        val spyFailureHandler = SpyFailureHandler()
        try {
            clickDisplayedView(withId(id), spyFailureHandler)
        } catch (noMatchingViewException: NoMatchingViewException) {
            try {
                clickOverflowListMenu(menuIdMatcher(id), spyFailureHandler)
            } catch (error: Exception) {
                spyFailureHandler.resendFirstError("Could not click menu id, neither as action or overflow")
            }
        }
    }

    @JvmStatic
    fun clickMenu(text: String) {
        val spyFailureHandler = SpyFailureHandler()
        try {
            clickDisplayedView(withText(text), spyFailureHandler)
        } catch (noMatchingViewException: NoMatchingViewException) {
            try {
                clickViewWithDescription(text, spyFailureHandler)
            } catch (noMatchingViewByTextException: NoMatchingViewException) {
                try {
                    clickOverflowListMenu(hasToString<String>(text), spyFailureHandler)
                } catch (error: Exception) {
                    spyFailureHandler.resendFirstError("Could not click menu title <$text>, neither as action or overflow")
                }
            }
        }
    }


    private fun clickDisplayedView(matcher: Matcher<View>, spyFailureHandler: SpyFailureHandler) {
        onView(displayedAnd(matcher)).withFailureHandler(spyFailureHandler).perform(click())
    }

    private fun clickViewWithDescription(text: String, spyFailureHandler: SpyFailureHandler) {
        onView(displayedAnd(withContentDescription(text))).withFailureHandler(spyFailureHandler).perform(click())
    }

    private fun clickOverflowListMenu(itemMatcher: Matcher<*>, spyFailureHandler: SpyFailureHandler) {
        openOverflow()
        withFailureHandler(spyFailureHandler) {
            onData(itemMatcher).inRoot(isPlatformPopup()).perform(click())
        }
    }

    private fun openOverflow() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext())
    }

}
