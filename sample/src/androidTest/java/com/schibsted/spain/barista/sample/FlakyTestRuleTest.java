package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.schibsted.spain.barista.rule.flaky.AllowFlaky;
import com.schibsted.spain.barista.rule.flaky.FlakyTestRule;
import com.schibsted.spain.barista.rule.flaky.Repeat;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FlakyTestRuleTest {

  private static boolean hasRunAllowFlakyTest = false;
  private static boolean hasRunRepeatTest = false;

  private ActivityTestRule<HelloWorldActivity> activityRule = new ActivityTestRule<>(HelloWorldActivity.class, true, false);
  private FlakyTestRule flakyRule = new FlakyTestRule()
      .allowFlakyAttemptsByDefault(1);

  @Rule
  public RuleChain chain = RuleChain.outerRule(flakyRule)
      .around(activityRule);

  // WARNING: this test must fail when run
  @Test
  @Repeat(times = 2)
  @Ignore
  public void repeatAnnotationTest() throws Exception {
    activityRule.launchActivity(null);

    onView(withId(R.id.some_view)).check(matches(isDisplayed()));

    if (hasRunRepeatTest) {
      throw new TestException("Test failure on second try");
    } else {
      hasRunRepeatTest = true;
    }
  }

  @Test
  @AllowFlaky(attempts = 2)
  public void allowFlakyAnnotationTest() throws Exception {
    activityRule.launchActivity(null);

    onView(withId(R.id.some_view)).check(matches(isDisplayed()));

    if (!hasRunAllowFlakyTest) {
      hasRunAllowFlakyTest = true;
      throw new TestException("Test failure on first try");
    }
  }

  @Test
  public void testWithoutAnnotations() throws Exception {
    activityRule.launchActivity(null);

    onView(withId(R.id.some_view)).check(matches(isDisplayed()));
  }

  private static class TestException extends Exception {
    TestException(String message) {
      super(message);
    }
  }
}
