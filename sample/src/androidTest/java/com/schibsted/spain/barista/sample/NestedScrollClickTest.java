package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaClickActions.click;

@RunWith(AndroidJUnit4.class)
public class NestedScrollClickTest {

  @Rule
  public ActivityTestRule<NestedScrollViewActivity> activityRule = new ActivityTestRule<>(NestedScrollViewActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

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
}
