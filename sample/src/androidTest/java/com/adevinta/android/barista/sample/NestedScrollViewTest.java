package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.adevinta.android.barista.interaction.BaristaScrollInteractions.scrollTo;

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
