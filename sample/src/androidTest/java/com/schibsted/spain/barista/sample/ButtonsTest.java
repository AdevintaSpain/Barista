package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;
import static com.schibsted.spain.barista.BaristaClickActions.click;
import static com.schibsted.spain.barista.BaristaClickActions.clickBack;
import static com.schibsted.spain.barista.BaristaClickActions.clickScrollingIfNeeded;

@RunWith(AndroidJUnit4.class)
public class ButtonsTest {

    @Rule
    public ActivityTestRule<FlowFirstScreen> activityRule = new ActivityTestRule<>(FlowFirstScreen.class);

    @Test
    public void checkClickById() {
        click(R.id.next);
        assertTextIsDisplayed("Hi! I'm the second screen!");
    }

    @Test
    public void checkClickAndScrollIfNeededById() {
        clickScrollingIfNeeded(R.id.next);
        assertTextIsDisplayed("Hi! I'm the second screen!");
    }

    @Test
    public void checkClickByText() {
        click("Next");
        assertTextIsDisplayed("Hi! I'm the second screen!");
    }

    @Test
    public void checkClickAndScrollIfNeededByText() {
        clickScrollingIfNeeded("Next");
        assertTextIsDisplayed("Hi! I'm the second screen!");
    }

    @Test
    public void checkScrollingClickById() {
        clickScrollingIfNeeded(R.id.really_far_away_button);
        assertTextIsDisplayed("Hi! I'm the second screen!");
    }

    @Test
    public void checkScrollingClickByText() {
        clickScrollingIfNeeded("Really far away button");
        assertTextIsDisplayed("Hi! I'm the second screen!");
    }

    @Test
    public void checkBackButton() {
        click("Next");
        assertTextIsDisplayed("Hi! I'm the second screen!");
        clickBack();
        assertTextIsDisplayed("Hi! I'm the first screen!");
    }
}
