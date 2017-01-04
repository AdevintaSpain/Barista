package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;
import static com.schibsted.spain.barista.BaristaAutoCompleteTextViewActions.writeToAutocompleteEditText;
import static com.schibsted.spain.barista.BaristaEditTextActions.writeToEditText;

@RunWith(AndroidJUnit4.class)
public class AutoCompleteTextViewTest {

    @Rule
    public ActivityTestRule<AutoCompleteTextViewActivity> activityRule = new ActivityTestRule<>(AutoCompleteTextViewActivity.class);

    @Test
    public void checkWriteOnAutocomplete_whenIsVisible() {
        writeToAutocompleteEditText(R.id.autocomplete, "Apple");
        assertTextIsDisplayed("Apple");
    }

    @Test
    public void checkWriteOnAutocomplete_whenScrollIsNeeded() {
        writeToAutocompleteEditText(R.id.autocomplete_very_far_away, "Apple");
        assertTextIsDisplayed("Apple");
    }

    @Test
    public void checkWriteOnAutocomplete_whenParentIsNotAScrollView() {
        writeToEditText(R.id.autocomplete_centered, "Hello!");
        assertTextIsDisplayed("Hello!");
    }
}
