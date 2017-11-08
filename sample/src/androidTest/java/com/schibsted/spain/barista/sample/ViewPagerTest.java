package com.schibsted.spain.barista.sample;

import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.rule.flaky.FlakyActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.action.BaristaViewPagerActions.swipeViewPagerBack;
import static com.schibsted.spain.barista.action.BaristaViewPagerActions.swipeViewPagerForward;

@RunWith(AndroidJUnit4.class)
public class ViewPagerTest {

  @Rule
  public FlakyActivityTestRule<ViewPagerWithTwoDifferentPagesActivity> activityRule =
      new FlakyActivityTestRule<>(ViewPagerWithTwoDifferentPagesActivity.class)
          .allowFlakyAttemptsByDefault(10);

  @Test
  public void checkSwipeForward() {
    swipeViewPagerForward(R.id.pager);

    assertDisplayed("2");
  }

  @Test
  public void checkSwipeBack() {
    swipeViewPagerForward(R.id.pager);
    swipeViewPagerBack(R.id.pager);

    assertDisplayed("1");
  }

  @Test
  public void swipingBackInTheFirstPageDoesntCrash() {
    swipeViewPagerBack(R.id.pager);

    assertDisplayed("1");
  }

  @Test
  public void swipingForwardInTheLastPageDoesntCrash() {
    swipeViewPagerForward(R.id.pager);
    swipeViewPagerForward(R.id.pager);

    assertDisplayed("2");
  }
}
