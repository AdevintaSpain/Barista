package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.adevinta.android.barista.internal.failurehandler.BaristaException;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.adevinta.android.barista.interaction.BaristaScrollInteractions.safelyScrollTo;
import static com.adevinta.android.barista.interaction.BaristaScrollInteractions.scrollTo;


public class ScrollsTest {

  @Rule
  public ActivityTestRule<FlowFirstScreen> activityRule = new ActivityTestRule<>(FlowFirstScreen.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void scrollsInsideScrollable_byCustomMatcher() {
    assertTopVisible();

    scrollTo(withId(R.id.really_far_away_button));

    assertBottomVisible();
  }

  @Test
  public void scrollsInsideScrollable_byId() {
    assertTopVisible();

    scrollTo(R.id.really_far_away_button);

    assertBottomVisible();
  }

  @Test
  public void scrollsInsideScrollable_byHardcodedText() {
    assertTopVisible();

    scrollTo("Really far away button");

    assertBottomVisible();
  }

  @Test
  public void scrollsInsideScrollable_byTextResource() {
    assertTopVisible();

    scrollTo(R.string.really_far_away_button);

    assertBottomVisible();
  }

  @Test(expected = BaristaException.class)
  public void scrollsOutsideScrollableFails() throws Exception {
    assertTopVisible();

    scrollTo(R.id.centered_button);
  }

  @Test
  public void safelyScrollsInsideScrollable_byCustomMatcher() throws Exception {
    assertTopVisible();

    safelyScrollTo(withId(R.id.really_far_away_button));

    assertBottomVisible();
  }

  @Test
  public void safelyScrollsInsideScrollable_byId() throws Exception {
    assertTopVisible();

    safelyScrollTo(R.id.really_far_away_button);

    assertBottomVisible();
  }

  @Test
  public void safelyScrollsInsideScrollable_byHardcodedText() throws Exception {
    assertTopVisible();

    safelyScrollTo("Really far away button");

    assertBottomVisible();
  }

  @Test
  public void safelyScrollsInsideScrollable_byTextResource() throws Exception {
    assertTopVisible();

    safelyScrollTo(R.string.really_far_away_button);

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
