package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.longClickOn;

@RunWith(AndroidJUnit4.class)
public class LongClickTest {

  @Rule
  public ActivityTestRule<FlowFirstScreen> activityRule = new ActivityTestRule<>(FlowFirstScreen.class);

  @Test
  public void checkLongClick_byId() {
    longClickOn(R.id.next);
    assertDisplayed("I was long pressed");
  }

  @Test
  public void checkLongClick_byText() {
    longClickOn("Next");
    assertDisplayed("I was long pressed");
  }

  @Test
  public void checkLongClick_byStringResource() {
    longClickOn(R.string.centered_button);
    assertDisplayed("I was long pressed");
  }

  @Test
  public void checkLongClickScrollsIfNeeded_byId() {
    longClickOn(R.id.really_far_away_button);
    assertDisplayed("I was long pressed");
  }

  @Test
  public void checkLongClickScrollsIfNeeded_byText() {
    longClickOn("Really far away button");
    assertDisplayed("I was long pressed");
  }

  @Test
  public void checkLongClickWhenParentIsNotAnScrollView_byId() {
    longClickOn(R.id.centered_button);
    assertDisplayed("I was long pressed");
  }

  @Test
  public void checkLongClickWhenParentIsNotAnScrollView_byText() {
    longClickOn("Centered button");
    assertDisplayed("I was long pressed");
  }
}
