package com.schibsted.spain.barista.sample;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static org.junit.Assert.assertEquals;

public class FailureHandlerTest {

  private static final String NON_EXISTING = "The cake is a lie";

  @Rule
  public ActivityTestRule<SomeViewsWithDifferentVisibilitiesActivity> activityRule =
      new ActivityTestRule<>(SomeViewsWithDifferentVisibilitiesActivity.class);

  private SpyFailureHandler baseFailureHandler;

  @Before
  public void setUp() throws Exception {
    baseFailureHandler = new SpyFailureHandler();
    Espresso.setFailureHandler(baseFailureHandler);
  }

  @Test
  public void baseFailureHandlerCalledOnce_withBarista() throws Exception {
    try {
      assertDisplayed(NON_EXISTING);
    } catch (RuntimeException ignored) {
    }

    assertBaseHandlerCalledOnce();
  }

  @Test
  public void baseFailureHandlerCalledOnce_withEspresso() throws Exception {
    try {
      onView(withText(NON_EXISTING)).check(matches(isDisplayed()));
    } catch (RuntimeException ignored) {
    }

    assertBaseHandlerCalledOnce();
  }

  private void assertBaseHandlerCalledOnce() {
    assertEquals(1, baseFailureHandler.getCapturedFailures().size());
  }
}
