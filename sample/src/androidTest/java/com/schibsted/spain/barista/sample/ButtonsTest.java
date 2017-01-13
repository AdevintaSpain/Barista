package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaClickActions.click;
import static com.schibsted.spain.barista.BaristaClickActions.clickBack;

@RunWith(AndroidJUnit4.class)
public class ButtonsTest {

  @Rule
  public ActivityTestRule<FlowFirstScreen> activityRule = new ActivityTestRule<>(FlowFirstScreen.class);

  @Test
  public void checkClickById() {
    click(R.id.next);
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClickByText() {
    click("Next");
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClickWhenParentIsNotAnScrollView() {
    click("Centered button");
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkBackButton() {
    click("Next");
    assertDisplayed("Hi! I'm the second screen!");
    clickBack();
    assertDisplayed("Hi! I'm the first screen!");
  }
}
