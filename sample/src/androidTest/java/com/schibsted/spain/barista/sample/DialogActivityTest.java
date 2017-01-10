package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;
import static com.schibsted.spain.barista.BaristaClickActions.click;
import static com.schibsted.spain.barista.BaristaDialogActions.clickDialogNegativeButton;
import static com.schibsted.spain.barista.BaristaDialogActions.clickDialogNeutralButton;
import static com.schibsted.spain.barista.BaristaDialogActions.clickDialogPositiveButton;

@RunWith(AndroidJUnit4.class)
public class DialogActivityTest {

    @Rule
    public ActivityTestRule<DialogActivity> activityRule = new ActivityTestRule<>(DialogActivity.class);

    @Before
    public void setup() {
        click(R.id.button);
    }

    @Test
    @Ignore
    public void positiveButton() {
        clickDialogPositiveButton();
        assertTextIsDisplayed("positive");
    }

    @Test
    public void negativeButton() {
        clickDialogNegativeButton();
        assertTextIsDisplayed("negative");
    }

    @Test
    public void neutralButton() {
        clickDialogNeutralButton();
        assertTextIsDisplayed("neutral");
    }
}
