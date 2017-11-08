package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaClickActions.click;
import static com.schibsted.spain.barista.BaristaClickActions.clickBack;

@RunWith(AndroidJUnit4.class)
public class ClickTest {

  @Rule
  public ActivityTestRule<FlowFirstScreen> activityRule = new ActivityTestRule<>(FlowFirstScreen.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkClick_byId() {
    click(R.id.next);
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClick_byText() {
    click("Next");
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClick_byStringResource() {
    click(R.string.centered_button);
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClickScrollsIfNeeded_byId() {
    click(R.id.really_far_away_button);
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClickScrollsIfNeeded_byText() {
    click("Really far away button");
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClickWhenParentIsNotAnScrollView_byId() {
    click(R.id.centered_button);
    assertDisplayed("Hi! I'm the second screen!");
  }

  @Test
  public void checkClickWhenParentIsNotAnScrollView_byText() {
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

  @Test
  public void checkClickOnHalfHiddenButton() throws Exception {
    click(R.id.half_hidden);
    assertDisplayed("Hi! I'm the second screen!");
  }
}
