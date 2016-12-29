package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;

@RunWith(AndroidJUnit4.class)
public class BaristaSampleTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void helloWorldIsDisplayedWhenLookingById() throws Exception {
        assertTextIsDisplayed(R.id.tv_hello_world);
    }

    @Test
    public void helloWorldIsDisplayedWhenLookingByText() throws Exception {
        assertTextIsDisplayed("Hello World!");
    }
}
