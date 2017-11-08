package com.schibsted.spain.barista

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v4.widget.NestedScrollView
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
import com.schibsted.spain.barista.custom.DisplayedMatchers.displayedAnd
import com.schibsted.spain.barista.custom.NestedEnabledScrollToAction.nestedScrollToAction
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import com.schibsted.spain.barista.internal.resourceMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf

object BaristaClickActions {

    @JvmStatic
    fun clickBack() {
        pressBack()
    }

    @JvmStatic
    fun clickOn(resId: Int) {
        performClickTypeOnMatcher(resId.resourceMatcher(), click())
    }

    @JvmStatic
    fun clickOn(text: String) {
        performClickTypeOnMatcher(withText(text), click())
    }

    @JvmStatic
    fun longClick(resId: Int) {
        performClickTypeOnMatcher(resId.resourceMatcher(), longClick())
    }

    @JvmStatic
    fun longClick(text: String) {
        performClickTypeOnMatcher(withText(text), longClick())
    }

    private fun performClickTypeOnMatcher(viewMatcher: Matcher<View>, clickType: ViewAction) {
        val spyHandler = SpyFailureHandler()
        try {
            try {
                performOnDisplayedView(viewMatcher, clickType, spyHandler)
            } catch (firstError: RuntimeException) {
                try {
                    scrollAndPerformOnView(viewMatcher, clickType, spyHandler)
                } catch (secondError: RuntimeException) {
                    scrollAndPerformOnDisplayedView(viewMatcher, clickType, spyHandler)
                }
            }
        } catch (fatalError: RuntimeException) {
            spyHandler.resendFirstError("Could not clickOn view ${viewMatcher.description()}")
        }
    }

    private fun scrollAndPerformOnView(viewMatcher: Matcher<View>, clickType: ViewAction, handler: SpyFailureHandler) {
        onView(viewMatcher).withFailureHandler(handler).perform(nestedScrollToAction(), clickType)
    }

    private fun scrollAndPerformOnDisplayedView(viewMatcher: Matcher<View>, clickType: ViewAction, failureHandler: SpyFailureHandler) {
        onView(allOf(
                viewMatcher,
                isDescendantOfA(allOf(
                        isDisplayed(),
                        anyOf(
                                isAssignableFrom(ScrollView::class.java),
                                isAssignableFrom(HorizontalScrollView::class.java),
                                isAssignableFrom(ListView::class.java),
                                isAssignableFrom(NestedScrollView::class.java)
                        )
                ))
        ))
                .withFailureHandler(failureHandler)
                .perform(scrollTo(), clickType)
    }

    private fun performOnDisplayedView(viewMatcher: Matcher<View>, clickType: ViewAction, failureHandler: SpyFailureHandler) {
        onView(displayedAnd(viewMatcher))
                .withFailureHandler(failureHandler)
                .perform(clickType)
    }
}