package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn;

public class NestedScrollClickTest {

  @Rule
  public ActivityTestRule<NestedScrollViewActivity> activityRule = new ActivityTestRule<>(NestedScrollViewActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

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
}
