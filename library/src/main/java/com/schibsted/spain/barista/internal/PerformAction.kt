package com.schibsted.spain.barista.internal

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.core.widget.NestedScrollView
import android.view.View
import android.widget.AbsListView
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedAnd
import com.schibsted.spain.barista.internal.viewaction.NestedEnabledScrollToAction.nestedScrollToAction
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf

/**
 * Extension function alias for [performActionOnView]
 */
fun Matcher<View>.performAction(action: ViewAction) {
  performActionOnView(viewMatcher = this, action = action)
}

/**
 * Performs the [action] on a view described by [viewMatcher]
 *
 * Attempts to perform it using multiple scenarios for the [viewMatcher]:
 * 1. One or more views match the [viewMatcher] but only one is currently displayed.
 * 2. Only one view matches the [viewMatcher] but needs to scroll first to display it.
 * 3. Multiple views matches the [viewMatcher] and need to scroll first, but only one scrollable view is displayed.
 */
fun performActionOnView(viewMatcher: Matcher<View>, action: ViewAction) {
  val spyHandler = SpyFailureHandler()
  try {
    try {
      tryToPerformOnDisplayedView(viewMatcher, action, spyHandler)
    } catch (firstError: RuntimeException) {
      try {
        tryToScrollAndPerformOnView(viewMatcher, action, spyHandler)
      } catch (secondError: RuntimeException) {
        tryToScrollAndPerformOnDisplayedParentView(viewMatcher, action, spyHandler)
      }
    }
  } catch (fatalError: RuntimeException) {
    spyHandler.resendFirstError("Could not perform action ${action.description} on view ${viewMatcher.description()}")
  }
}

private fun tryToPerformOnDisplayedView(viewMatcher: Matcher<View>, action: ViewAction, failureHandler: SpyFailureHandler) {
  onView(displayedAnd(viewMatcher))
      .withFailureHandler(failureHandler)
      .perform(action)
}

private fun tryToScrollAndPerformOnView(viewMatcher: Matcher<View>, action: ViewAction, handler: SpyFailureHandler) {
  onView(viewMatcher)
      .withFailureHandler(handler)
      .perform(nestedScrollToAction(), action)
}

private fun tryToScrollAndPerformOnDisplayedParentView(viewMatcher: Matcher<View>, action: ViewAction, failureHandler: SpyFailureHandler) {
  onView(allOf(
      viewMatcher,
      isDescendantOfA(allOf(
          isDisplayed(),
          anyOf(
              isAssignableFrom(ScrollView::class.java),
              isAssignableFrom(HorizontalScrollView::class.java),
              isAssignableFrom(AbsListView::class.java),
              isAssignableFrom(NestedScrollView::class.java)
          )
      ))
  ))
      .withFailureHandler(failureHandler)
      .perform(scrollTo(), action)
}
