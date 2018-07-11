package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaAutoCompleteTextViewInteractions.writeToAutoComplete;
import static com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo;

@RunWith(AndroidJUnit4.class)
public class SearchViewTest {

    @Rule
    public ActivityTestRule<SearchViewActivity> activityRule = new ActivityTestRule<>(SearchViewActivity.class);

    @Rule
    public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

    @Test
    public void checkWriteTo_whenIsVisible() {
        writeTo(R.id.searchview, "Apple");
        assertDisplayed(R.id.searchResult, "Apple");
    }

    @Test
    public void checkWriteTo_whenScrollIsNeeded() {
        writeTo(R.id.searchview_very_far_away, "Banana");
        assertDisplayed(R.id.searchResult, "Banana");
    }

    @Test
    public void checkWriteOnAutoComplete_whenIsVisible() {
        writeToAutoComplete(R.id.searchview, "Apple");
        assertDisplayed(R.id.searchResult, "Apple");
    }

    @Test
    public void checkWriteOnAutoComplete_whenScrollIsNeeded() {
        writeToAutoComplete(R.id.searchview_very_far_away, "Banana");
        assertDisplayed(R.id.searchResult, "Banana");
    }
}
