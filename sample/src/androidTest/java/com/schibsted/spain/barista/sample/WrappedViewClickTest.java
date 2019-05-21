package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.internal.viewaction.PerformClickAction.clickUsingPerformClick;

public class WrappedViewClickTest {

  @Rule
  public ActivityTestRule<WrappedViewActivity> activityTestRule =
      new ActivityTestRule<>(WrappedViewActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void clickOnButton() throws Exception {
    onView(withId(R.id.button)).perform(click());

    assertDisplayed("Clicked");
  }

  @Test
  public void clickOnButtonWithCustomAction() throws Exception {
    onView(withId(R.id.button)).perform(clickUsingPerformClick());

    assertDisplayed("Clicked");
  }

  @Test
  public void clickOnButtonWrapper() throws Exception {
    onView(withId(R.id.button_wrapper)).perform(click());

    assertDisplayed("Clicked");
  }

  @Test
  public void clickOnButtonWrapperWithCustomAction() throws Exception {
    onView(withId(R.id.button_wrapper)).perform(clickUsingPerformClick());

    assertDisplayed("Clicked");
  }
}