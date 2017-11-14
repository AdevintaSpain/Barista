package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickBack;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;

@RunWith(AndroidJUnit4.class)
public class ClickTest {

  @Rule
  public ActivityTestRule<FlowFirstScreen> activityRule = new ActivityTestRule<>(FlowFirstScreen.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkClick_byId() {
    clickOn(R.id.next);
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClick_byText() {
    clickOn("Next");
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClick_byStringResource() {
    clickOn(R.string.centered_button);
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClickScrollsIfNeeded_byId() {
    clickOn(R.id.really_far_away_button);
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClickScrollsIfNeeded_byText() {
    clickOn("Really far away button");
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClickWhenParentIsNotAnScrollView_byId() {
    clickOn(R.id.centered_button);
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClickWhenParentIsNotAnScrollView_byText() {
    clickOn("Centered button");
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkBackButton() {
    clickOn("Next");
    assertDisplayed("Hi! I'm the second screen!");
    clickBack();
    assertDisplayed("Hi! I'm the first screen!");
  }

  @Test
  public void checkClickOnHalfHiddenButton() throws Exception {
    clickOn(R.id.half_hidden);
    assertDisplayed("Hi! I'm the second screen!");
  }
}
