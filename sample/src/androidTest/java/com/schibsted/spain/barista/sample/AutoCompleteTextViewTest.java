package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaAutoCompleteTextViewInteractions.writeToAutoComplete;

@RunWith(AndroidJUnit4.class)
public class AutoCompleteTextViewTest {

  @Rule
  public ActivityTestRule<AutoCompleteTextViewActivity> activityRule = new ActivityTestRule<>(AutoCompleteTextViewActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkWriteOnAutoComplete_whenIsVisible() {
    writeToAutoComplete(R.id.autocomplete, "Apple");
    assertDisplayed("Apple");
  }

  @Test
  public void checkWriteOnAutoComplete_whenScrollIsNeeded() {
    writeToAutoComplete(R.id.autocomplete_very_far_away, "Apple");
    assertDisplayed("Apple");
  }

  @Test
  public void checkWriteOnAutoComplete_whenParentIsNotAScrollView() {
    writeToAutoComplete(R.id.autocomplete_centered, "Hello!");
    assertDisplayed("Hello!");
  }
}
