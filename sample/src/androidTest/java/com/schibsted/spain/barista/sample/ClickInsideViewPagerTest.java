package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;

@RunWith(AndroidJUnit4.class)
public class ClickInsideViewPagerTest {

  @Rule
  public ActivityTestRule<ViewPagerWithFiveSamePagesActivity> activityRule =
      new ActivityTestRule<>(ViewPagerWithFiveSamePagesActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void clickWorksAlsoWhenButtonIsRepeatedInMultipleViewPagerViews_byId() {
    clickOn(R.id.button);
    assertDisplayed(R.string.click);
  }

  @Test
  public void clickWorksAlsoWhenButtonIsRepeatedInMultipleViewPagerViews_byText() {
    clickOn("Centered button");
    assertDisplayed(R.string.click);
  }

  @Test
  public void clickWorksAlsoWhenButtonIsRepeatedInMultipleViewPagerViews_belowScroll_byText() {
    clickOn("Really far away button");
    assertDisplayed(R.string.click_far_away);
  }
}
