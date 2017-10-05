package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaClickActions.click;

@RunWith(AndroidJUnit4.class)
public class ClickInsideViewPagerTest {

  @Rule
  public ActivityTestRule<ViewPagerWithFiveSamePagesActivity> activityRule =
      new ActivityTestRule<>(ViewPagerWithFiveSamePagesActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void clickWorksAlsoWhenButtonIsRepeatedInMultipleViewPagerViews_byId() {
    click(R.id.button);
    assertDisplayed(R.string.click);
  }

  @Test
  public void clickWorksAlsoWhenButtonIsRepeatedInMultipleViewPagerViews_byText() {
    click("Centered button");
    assertDisplayed(R.string.click);
  }

  @Test
  public void clickWorksAlsoWhenButtonIsRepeatedInMultipleViewPagerViews_belowScroll_byText() {
    click("Really far away button");
    assertDisplayed(R.string.click_far_away);
  }
}
