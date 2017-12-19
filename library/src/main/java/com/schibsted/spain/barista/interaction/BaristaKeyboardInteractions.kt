package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.hasFocus
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.v4.widget.NestedScrollView
import android.view.View
import android.widget.EditText
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers
import com.schibsted.spain.barista.internal.viewaction.NestedEnabledScrollToAction
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf.allOf

object BaristaKeyboardInteractions {

  @JvmStatic
  fun closeKeyboard() {
    onView(isRoot()).perform(closeSoftKeyboard())
  }

  @JvmStatic
  @JvmOverloads
  fun pressImeActionButton(@IdRes editTextId: Int? = null) {
    val matcher = findEditText(editTextId)
    performPressImeActionButton(matcher, ViewActions.pressImeActionButton())
  }

  private fun findEditText(editTextId: Int?) = when (editTextId) {
    null -> allOf(ViewMatchers.isAssignableFrom(EditText::class.java), hasFocus())
    else -> withId(editTextId)
  }

  private fun performPressImeActionButton(viewMatcher: Matcher<View>, clickType: ViewAction) {
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
      spyHandler.resendFirstError("Could not click on view ${viewMatcher.description()}")
    }
  }

  private fun scrollAndPerformOnView(viewMatcher: Matcher<View>, clickType: ViewAction, handler: SpyFailureHandler) {
    onView(viewMatcher).withFailureHandler(handler).perform(NestedEnabledScrollToAction.nestedScrollToAction(),
        clickType)
  }

  private fun scrollAndPerformOnDisplayedView(viewMatcher: Matcher<View>, clickType: ViewAction,
      failureHandler: SpyFailureHandler) {
    onView(Matchers.allOf(
        viewMatcher,
        ViewMatchers.isDescendantOfA(Matchers.allOf(
            ViewMatchers.isDisplayed(),
            Matchers.anyOf(
                ViewMatchers.isAssignableFrom(ScrollView::class.java),
                ViewMatchers.isAssignableFrom(HorizontalScrollView::class.java),
                ViewMatchers.isAssignableFrom(ListView::class.java),
                ViewMatchers.isAssignableFrom(NestedScrollView::class.java)
            )
        ))
    ))
        .withFailureHandler(failureHandler)
        .perform(ViewActions.scrollTo(), clickType)
  }

  private fun performOnDisplayedView(viewMatcher: Matcher<View>, clickType: ViewAction,
      failureHandler: SpyFailureHandler) {
    onView(DisplayedMatchers.displayedAnd(viewMatcher))
        .withFailureHandler(failureHandler)
        .perform(clickType)
  }
}
