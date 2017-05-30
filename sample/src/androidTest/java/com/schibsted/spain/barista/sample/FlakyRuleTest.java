package com.schibsted.spain.barista.sample;

import com.schibsted.spain.barista.cleardata.MonitorRule;
import com.schibsted.spain.barista.flakyespresso.AllowFlaky;
import com.schibsted.spain.barista.flakyespresso.FlakyActivityTestRule;
import com.schibsted.spain.barista.flakyespresso.Repeat;
import java.util.Random;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class FlakyRuleTest {

  private final Random random = new Random();

  @Rule
  public MonitorRule monitorRule = new MonitorRule();

  @Rule
  public FlakyActivityTestRule<HelloWorldActivity> activityRule = new FlakyActivityTestRule<>(HelloWorldActivity.class, true, false);

  @Rule
  public FlakyActivityTestRule<HelloWorldActivity> activityRuleWithDefaultFlaky =
      new FlakyActivityTestRule<>(HelloWorldActivity.class, true, false)
          .allowFlakyAttemptsByDefault(5);

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
  @AllowFlaky(attempts = 5)
  public void someFlakyTest() throws Exception {
    activityRule.launchActivity(null);

    onView(withId(R.id.some_view)).check(matches(isDisplayed()));

    if (random.nextFloat() > 0.3) {
      throw new TestException("Random test failure");
    }
  }

  @Test
  public void someDefaultFlakyTest() throws Exception {
    activityRuleWithDefaultFlaky.launchActivity(null);

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
