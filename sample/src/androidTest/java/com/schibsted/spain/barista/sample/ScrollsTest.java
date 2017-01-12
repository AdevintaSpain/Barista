package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.BaristaScrollActions.scrollTo;

@RunWith(AndroidJUnit4.class)
public class ScrollsTest {

  @Rule
  public ActivityTestRule<FlowFirstScreen> activityRule = new ActivityTestRule<>(FlowFirstScreen.class);

  @Test
  public void checkScrollByText() {
    assertDisplayed("Hi! I'm the first screen!");
    assertNotDisplayed("Really far away button");

    scrollTo("Really far away button");

    assertNotDisplayed("Hi! I'm the first screen!");
    assertDisplayed("Really far away button");
  }

  @Test
  public void checkScrollById() {
    assertDisplayed("Hi! I'm the first screen!");
    assertNotDisplayed("Really far away button");

    scrollTo(R.id.really_far_away_button);

    assertNotDisplayed("Hi! I'm the first screen!");
    assertDisplayed("Really far away button");
  }
}
