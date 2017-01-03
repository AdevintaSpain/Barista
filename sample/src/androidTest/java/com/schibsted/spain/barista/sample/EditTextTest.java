package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.BaristaEditTextActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;

@RunWith(AndroidJUnit4.class)
public class EditTextTest {

    @Rule
    public ActivityTestRule<EditTextActivity> activityRule = new ActivityTestRule<>(EditTextActivity.class);

    @Test
    public void checkWriteOnEditText_whenEditTextIsVisible() {
        BaristaEditTextActions.writeToEditText(R.id.edittext, "Hello!");
        assertTextIsDisplayed("Hello!");
    }

    @Test
    public void checkWriteOnEditText_whenScrollIsNeeded() {
        BaristaEditTextActions.writeToEditText(R.id.edittext_very_far_away, "Hello!");
        assertTextIsDisplayed("Hello!");
    }
}
