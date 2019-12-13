package com.schibsted.spain.barista.sample;


import android.view.View;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.internal.failurehandler.BaristaException;
import com.schibsted.spain.barista.internal.matcher.HelperMatchers;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;

@RunWith(AndroidJUnit4.class)
public class AtPositionTest {

    private static final int THIRD_POSITION = 2;
    private static final String THIRD_POSITION_TEXT = "3";

    @Rule
    public ActivityTestRule<TestAtPositionActivity> activityRule =
            new ActivityTestRule<>(TestAtPositionActivity.class);


    /**
     * This test succeeds even when the counter used in atPosition is not set back to 0
     */
    @Test
    public void useViewElementFoundWithAtPositionOnce() {
        Matcher<View> thirdTextView = HelperMatchers.atPosition(THIRD_POSITION, ViewMatchers.isAssignableFrom(TextView.class));
        assertDisplayed(thirdTextView);
    }

    /**
     * This test fails if the counter used in atPosition is not set back to 0
     */
    @Test
    public void useViewElementFoundWithAtPositionTwice() {
        Matcher<View> thirdTextView = HelperMatchers.atPosition(THIRD_POSITION, ViewMatchers.isAssignableFrom(TextView.class));
        assertDisplayed(thirdTextView);
        assertDisplayed(thirdTextView);
        assertDisplayed(THIRD_POSITION_TEXT);
    }

}