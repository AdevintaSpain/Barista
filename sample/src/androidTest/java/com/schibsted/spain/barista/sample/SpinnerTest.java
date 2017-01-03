package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;
import static com.schibsted.spain.barista.BaristaSpinnerActions.chooseSpinnerPosition;

@RunWith(AndroidJUnit4.class)
public class SpinnerTest {

    @Rule
    public ActivityTestRule<SpinnerActivity> activityRule = new ActivityTestRule<>(SpinnerActivity.class);

    @Test
    public void checkSelectSpinnerPosition() {
        chooseSpinnerPosition(R.id.spinner, String.class, 0);
        assertTextIsDisplayed("Banana");
    }

    @Test
    public void checkSelectSpinnerThirdPosition() {
        chooseSpinnerPosition(R.id.spinner, String.class, 2);
        assertTextIsDisplayed("Orange");
    }
}
