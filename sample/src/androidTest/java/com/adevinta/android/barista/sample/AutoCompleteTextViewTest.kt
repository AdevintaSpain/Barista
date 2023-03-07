package com.adevinta.android.barista.sample

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaAutoCompleteTextViewInteractions.assertAutocompleteSuggestion
import com.adevinta.android.barista.interaction.BaristaAutoCompleteTextViewInteractions.writeToAutoComplete
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule
import org.junit.Rule
import org.junit.Test


class AutoCompleteTextViewTest {
  @Rule
  var activityRule = ActivityTestRule(
    AutoCompleteTextViewActivity::class.java
  )

  @Rule
  var handlerValidator = FailureHandlerValidatorRule()

  @Test
  fun checkWriteOnAutoComplete_whenIsVisible() {
    writeToAutoComplete(R.id.autocomplete, "Apple")
    assertDisplayed("Apple")
  }

  @Test
  fun checkWriteOnAutoComplete_whenScrollIsNeeded() {
    writeToAutoComplete(R.id.autocomplete_very_far_away, "Apple")
    assertDisplayed("Apple")
  }

  @Test
  fun checkWriteOnAutoComplete_whenParentIsNotAScrollView() {
    writeToAutoComplete(R.id.autocomplete_centered, "Hello!")
    assertDisplayed("Hello!")
  }

  @Test
  fun checkWriteOnAutoComplete_proposeOneChoice() {
    clickOn(R.id.autocomplete)
    assertAutocompleteSuggestion(withId(R.id.autocomplete), "Ap", "Apple")
  }

  @Test
  fun checkWriteOnAutoComplete_proposeMultipleChoise() {
    clickOn(R.id.autocomplete)
    assertAutocompleteSuggestion(withId(R.id.autocomplete), "B", listOf("Blueberry", "Banana"))
  }
}