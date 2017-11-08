package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.BaristaScrollActions.scrollTo;

@RunWith(AndroidJUnit4.class)
public class NestedScrollViewTest {

  @Rule
  public ActivityTestRule<NestedScrollViewActivity> activityRule = new ActivityTestRule<>(NestedScrollViewActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

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
