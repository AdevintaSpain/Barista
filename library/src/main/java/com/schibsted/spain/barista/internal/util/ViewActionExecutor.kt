package com.schibsted.spain.barista.internal.util

import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v4.widget.NestedScrollView
import android.view.View
import android.widget.AbsListView
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers
import com.schibsted.spain.barista.internal.viewaction.NestedEnabledScrollToAction
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object ViewActionExecutor {

  @JvmStatic
  fun performAction(viewMatcher: Matcher<View>, action: ViewAction) {
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
    Espresso.onView(viewMatcher).withFailureHandler(handler).perform(
        NestedEnabledScrollToAction.nestedScrollToAction(), action)
  }

  private fun scrollAndPerformOnDisplayedView(viewMatcher: Matcher<View>, action: ViewAction,
      failureHandler: SpyFailureHandler) {
    Espresso.onView(Matchers.allOf(
        viewMatcher,
        ViewMatchers.isDescendantOfA(Matchers.allOf(
            ViewMatchers.isDisplayed(),
            Matchers.anyOf(
                ViewMatchers.isAssignableFrom(ScrollView::class.java),
                ViewMatchers.isAssignableFrom(HorizontalScrollView::class.java),
                ViewMatchers.isAssignableFrom(AbsListView::class.java),
                ViewMatchers.isAssignableFrom(NestedScrollView::class.java)
            )
        ))
    ))
        .withFailureHandler(failureHandler)
        .perform(ViewActions.scrollTo(), action)
  }

  private fun performOnDisplayedView(viewMatcher: Matcher<View>, action: ViewAction,
      failureHandler: SpyFailureHandler) {
    Espresso.onView(DisplayedMatchers.displayedAnd(viewMatcher))
        .withFailureHandler(failureHandler)
        .perform(action)
  }
}
