package com.adevinta.android.barista.interaction

import android.view.View
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.internal.performAction
import com.adevinta.android.barista.internal.viewaction.AutoCompleteViewActions.replaceAutoComplete
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf

object BaristaAutoCompleteTextViewInteractions {

  @JvmStatic
  fun writeToAutoComplete(@IdRes autoCompleteId: Int, text: String) {
    val withId = withId(autoCompleteId)
    val assignableFrom = isAssignableFrom(EditText::class.java)
    val simpleMatcher = allOf(withId, assignableFrom)
    val wrapperMatcher = allOf(isDescendantOfA(withId), assignableFrom)
    val combinedMatcher = anyOf(simpleMatcher, wrapperMatcher)
    combinedMatcher.performAction(replaceAutoComplete(text))
  }

  /**
   * Trigger suggestion displayed on the matched view from the provided string
   */
  @JvmStatic
  fun triggerAutocompleteSuggestion(matcher: Matcher<View>, text: String) {
    clickOn(matcher)
    onView(
      matcher
    ).perform(
      replaceText(text),
      ViewActions.closeSoftKeyboard()
    )
  }

  /**
   * Assert that the expectedSuggestion string is displayed when we fill the matched view with the text.
   */
  @JvmStatic
  fun assertAutocompleteSuggestion(matcher: Matcher<View>, text: String, expectedSuggestion: String) {
    triggerAutocompleteSuggestion(matcher, text)
    onView(withText(expectedSuggestion))
      .inRoot(RootMatchers.isPlatformPopup())
      .check(ViewAssertions.matches(isDisplayed()))
  }

  /**
   * Assert that all the expectedSuggestions strings are displayed when we fill the matched view with the text.
   */
  @JvmStatic
  fun assertAutocompleteSuggestion(matcher: Matcher<View>, text: String, expectedSuggestions: List<String>) {
    triggerAutocompleteSuggestion(matcher, text)
    expectedSuggestions.forEach {
      onView(withText(it))
        .inRoot(RootMatchers.isPlatformPopup())
        .check(ViewAssertions.matches(isDisplayed()))
    }

  }
}
