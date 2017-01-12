package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaAutoCompleteTextViewActions.writeToAutoCompleteTextView;

@RunWith(AndroidJUnit4.class)
public class AutoCompleteTextViewTest {

  @Rule
  public ActivityTestRule<AutoCompleteTextViewActivity> activityRule = new ActivityTestRule<>(AutoCompleteTextViewActivity.class);

  @Test
  public void checkWriteOnAutocomplete_whenIsVisible() {
    writeToAutoCompleteTextView(R.id.autocomplete, "Apple");
    assertDisplayed("Apple");
  }

  @Test
  public void checkWriteOnAutocomplete_whenScrollIsNeeded() {
    writeToAutoCompleteTextView(R.id.autocomplete_very_far_away, "Apple");
    assertDisplayed("Apple");
  }

  @Test
  public void checkWriteOnAutocomplete_whenParentIsNotAScrollView() {
    writeToAutoCompleteTextView(R.id.autocomplete_centered, "Hello!");
    assertDisplayed("Hello!");
  }
}
