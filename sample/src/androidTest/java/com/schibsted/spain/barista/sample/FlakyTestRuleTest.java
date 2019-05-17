package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.schibsted.spain.barista.rule.flaky.AllowFlaky;
import com.schibsted.spain.barista.rule.flaky.FlakyTestRule;
import com.schibsted.spain.barista.rule.flaky.Repeat;
import java.util.Random;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FlakyTestRuleTest {

  private final Random random = new Random();

  private ActivityTestRule<HelloWorldActivity> activityRule = new ActivityTestRule<>(HelloWorldActivity.class, true, false);
  private FlakyTestRule flakyRule = new FlakyTestRule()
      .allowFlakyAttemptsByDefault(1);

  @Rule
  public RuleChain chain = RuleChain.outerRule(flakyRule)
      .around(activityRule);

  // WARNING: this test must fail when run
  @Test
  @Repeat(times = 5)
  @Ignore
  public void someImportantFlakyTest() throws Exception {
    activityRule.launchActivity(null);

    onView(withId(R.id.some_view)).check(matches(isDisplayed()));

    if (random.nextFloat() > 0.3) {
      throw new TestException("Random test failure");
    }
  }

  @Test
  @AllowFlaky(attempts = 10)
  public void someFlakyTest() throws Exception {
    activityRule.launchActivity(null);

    onView(withId(R.id.some_view)).check(matches(isDisplayed()));

    if (random.nextFloat() > 0.3) {
      throw new TestException("Random test failure");
    }
  }

  @Test
  public void someDeterministicTest() throws Exception {
    activityRule.launchActivity(null);

    onView(withId(R.id.some_view)).check(matches(isDisplayed()));
  }

  private static class TestException extends Exception {
    TestException(String message) {
      super(message);
    }
  }
}
