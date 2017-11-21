package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.internal.failurehandler.BaristaException;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaScrollInteractions.safelyScrollTo;
import static com.schibsted.spain.barista.interaction.BaristaScrollInteractions.scrollTo;

@RunWith(AndroidJUnit4.class)
public class ScrollsTest {

  @Rule
  public ActivityTestRule<FlowFirstScreen> activityRule = new ActivityTestRule<>(FlowFirstScreen.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void scrollsInsideScrollable_byId() {
    assertPageUnscrolled();
    scrollTo(R.id.really_far_away_button);
    assertPageSuccessfullyScrolled();
  }

  @Test
  public void scrollsInsideScrollable_byHardcodedText() {
    assertPageUnscrolled();
    scrollTo("Really far away button");
    assertPageSuccessfullyScrolled();
  }

  @Test
  public void scrollsInsideScrollable_byTextResource() {
    assertPageUnscrolled();
    scrollTo(R.string.really_far_away_button);
    assertPageSuccessfullyScrolled();
  }

  @Test(expected = BaristaException.class)
  public void scrollsOutsideScrollableFails() throws Exception {
    assertPageUnscrolled();
    scrollTo(R.id.centered_button);
    assertPageUnscrolled();
  }

  @Test
  public void safelyScrollsInsideScrollable_byId() throws Exception {
    assertPageUnscrolled();
    safelyScrollTo(R.id.really_far_away_button);
    assertPageSuccessfullyScrolled();
  }

  @Test
  public void safelyScrollsInsideScrollable_byHardcodedText() throws Exception {
    assertPageUnscrolled();
    safelyScrollTo("Really far away button");
    assertPageSuccessfullyScrolled();
  }

  @Test
  public void safelyScrollsInsideScrollable_byTextResource() throws Exception {
    assertPageUnscrolled();
    safelyScrollTo(R.string.really_far_away_button);
    assertPageSuccessfullyScrolled();
  }

  @Test
  public void safelyScrollOutsideScrollableDoesNotFail() throws Exception {
    assertPageUnscrolled();
    safelyScrollTo(R.id.centered_button);
    assertPageUnscrolled();
  }

  private void assertPageSuccessfullyScrolled() {
    assertNotDisplayed("Hi! I'm the first screen!");
    assertDisplayed("Really far away button");
  }

  private void assertPageUnscrolled() {
    assertDisplayed("Hi! I'm the first screen!");
    assertNotDisplayed("Really far away button");
  }
}
