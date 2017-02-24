package com.schibsted.spain.barista.sample;

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

public class FlakyActivityTest {

  private final Random random = new Random();

  @Rule
  public FlakyActivityTestRule<FlakyActivity> activityRule = new FlakyActivityTestRule<>(FlakyActivity.class, true, false);

  // WARNING: this test must fail when run
  @Test
  @Repeat(times = 5)
  @Ignore
  public void some_important_flaky_test() throws Exception {
    activityRule.launchActivity(null);

    onView(withId(R.id.some_view)).check(matches(isDisplayed()));

    if (random.nextFloat() > 0.3) {
      throw new TestException("Random test failure");
    }
  }

  @Test
  @AllowFlaky(attempts = 5)
  public void some_flaky_test() throws Exception {
    activityRule.launchActivity(null);

    onView(withId(R.id.some_view)).check(matches(isDisplayed()));

    if (random.nextFloat() > 0.3) {
      throw new TestException("Random test failure");
    }
  }

  @Test
  public void some_deterministic_test() throws Exception {
    activityRule.launchActivity(null);

    onView(withId(R.id.some_view)).check(matches(isDisplayed()));
  }

  private static class TestException extends Exception {
    public TestException(String message) {
      super(message);
    }
  }
}
