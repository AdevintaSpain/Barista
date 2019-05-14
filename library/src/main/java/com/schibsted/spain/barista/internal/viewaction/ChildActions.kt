package com.schibsted.spain.barista.internal.viewaction

import android.support.annotation.IdRes
import android.support.test.espresso.PerformException
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.util.HumanReadables
import android.view.View
import com.schibsted.spain.barista.internal.failurehandler.description
import com.schibsted.spain.barista.internal.util.ViewTreeAnalyzer
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

object ChildActions {

  @JvmStatic
  fun performOnChildWithId(@IdRes childId: Int, viewAction: ViewAction): ViewAction {
    return performOnChildWithMatcher(withId(childId), viewAction)
  }

  @JvmStatic
  fun performOnChildWithMatcher(childMatcher: Matcher<View>, viewAction: ViewAction): ViewAction {
    return object : ViewAction {
      override fun getConstraints(): Matcher<View> {
        return allOf<View>(isDisplayed(), hasDescendant(childMatcher))
      }

      override fun getDescription(): String {
        return "Perform " + viewAction.description + " on a child view " + childMatcher.description()
      }

      override fun perform(uiController: UiController, view: View) {
        var foundTarget = false

        for (child in ViewTreeAnalyzer.getAllChildren(view)) {
          if (childMatcher.matches(child)) {
            foundTarget = true
            viewAction.perform(uiController, child)
            break
          }
        }

        if (!foundTarget) {
          throw PerformException.Builder()
              .withActionDescription(description)
              .withViewDescription(HumanReadables.describe(view))
              .withCause(IllegalArgumentException("Didn't find any view " + childMatcher.description()))
              .build()
        }
      }
    }
  }
}
