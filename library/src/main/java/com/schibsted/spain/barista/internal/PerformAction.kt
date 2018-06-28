package com.schibsted.spain.barista.internal

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.v4.widget.NestedScrollView
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

fun Matcher<View>.performAction(action: ViewAction) {
  performActionOnView(viewMatcher = this, action = action)
}

fun performActionOnView(viewMatcher: Matcher<View>, action: ViewAction) {
  val spyHandler = SpyFailureHandler()
  try {
    try {
      performOnDisplayedView(viewMatcher, action, spyHandler)
    } catch (firstError: RuntimeException) {
      try {
        scrollAndPerformOnView(viewMatcher, action, spyHandler)
      } catch (secondError: RuntimeException) {
        scrollAndPerformOnDisplayedView(viewMatcher, action, spyHandler)
      }
    }
  } catch (fatalError: RuntimeException) {
    spyHandler.resendFirstError("Could not perform action ${action.description} on view ${viewMatcher.description()}")
  }
}

private fun scrollAndPerformOnView(viewMatcher: Matcher<View>, action: ViewAction, handler: SpyFailureHandler) {
  onView(viewMatcher)
      .withFailureHandler(handler)
      .perform(nestedScrollToAction(), action)
}

private fun scrollAndPerformOnDisplayedView(viewMatcher: Matcher<View>, action: ViewAction, failureHandler: SpyFailureHandler) {
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

private fun performOnDisplayedView(viewMatcher: Matcher<View>, action: ViewAction, failureHandler: SpyFailureHandler) {
  onView(displayedAnd(viewMatcher))
      .withFailureHandler(failureHandler)
      .perform(action)
}
