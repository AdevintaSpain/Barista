package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import com.schibsted.spain.barista.internal.failurehandler.BaristaException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.BaristaScrollActions.safelyScrollTo;
import static com.schibsted.spain.barista.BaristaScrollActions.scrollTo;

@RunWith(AndroidJUnit4.class)
public class ScrollsTest {

  @Rule
  public ActivityTestRule<FlowFirstScreen> activityRule = new ActivityTestRule<>(FlowFirstScreen.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void scrollsInsideScrollable() {
    assertTopVisible();

    scrollTo(R.id.really_far_away_button);

    assertBottomVisible();
  }

  @Test(expected = BaristaException.class)
  public void scrollsOutsideScrollableFails() throws Exception {
    assertTopVisible();

    scrollTo(R.id.centered_button);
  }

  @Test
  public void safelyScrollsInsideScrollable() throws Exception {
    assertTopVisible();

    safelyScrollTo(R.id.really_far_away_button);

    assertBottomVisible();
  }

  @Test
  public void safelyScrollOutsideScrollableDoesNotFail() throws Exception {
    assertTopVisible();

    safelyScrollTo(R.id.centered_button);

    assertTopVisible();
  }

  private void assertTopVisible() {
    assertDisplayed("Hi! I'm the first screen!");
    assertNotDisplayed("Really far away button");
  }

  private void assertBottomVisible() {
    assertNotDisplayed("Hi! I'm the first screen!");
    assertDisplayed("Really far away button");
  }
}
