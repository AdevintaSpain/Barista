package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaClickInteractions.longClickOn;

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
