package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaClickActions.longClick;

@RunWith(AndroidJUnit4.class)
public class LongClickTest {

  @Rule
  public ActivityTestRule<FlowFirstScreen> activityRule = new ActivityTestRule<>(FlowFirstScreen.class);

  @Test
  public void checkLongClick_byId() {
    longClick(R.id.next);
    assertDisplayed("I was long pressed");
  }

  @Test
  public void checkLongClick_byText() {
    longClick("Next");
    assertDisplayed("I was long pressed");
  }

  @Test
  public void checkClick_byStringResource() {
    longClick(R.string.centered_button);
    assertDisplayed("I was long pressed");
  }

  @Test
  public void checkClickScrollsIfNeeded_byId() {
    longClick(R.id.really_far_away_button);
    assertDisplayed("I was long pressed");
  }

  @Test
  public void checkClickScrollsIfNeeded_byText() {
    longClick("Really far away button");
    assertDisplayed("I was long pressed");
  }

  @Test
  public void checkClickWhenParentIsNotAnScrollView_byId() {
    longClick(R.id.centered_button);
    assertDisplayed("I was long pressed");
  }

  @Test
  public void checkClickWhenParentIsNotAnScrollView_byText() {
    longClick("Centered button");
    assertDisplayed("I was long pressed");
  }

}
