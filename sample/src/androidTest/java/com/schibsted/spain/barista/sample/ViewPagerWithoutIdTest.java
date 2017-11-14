package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.action.BaristaViewPagerActions.swipeViewPagerBack;
import static com.schibsted.spain.barista.action.BaristaViewPagerActions.swipeViewPagerForward;
import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertDisplayed;

@RunWith(AndroidJUnit4.class)
public class ViewPagerWithoutIdTest {

  @Rule
  public ActivityTestRule<ViewPagerWithTwoDifferentPagesActivity> activityRule =
      new ActivityTestRule<>(ViewPagerWithTwoDifferentPagesActivity.class);

  @Test
  public void checkSwipeForward() {
    swipeViewPagerForward();

    assertDisplayed("2");
  }

  @Test
  public void checkSwipeBack() {
    swipeViewPagerForward();
    swipeViewPagerBack();

    assertDisplayed("1");
  }

  @Test
  public void swipingBackInTheFirstPageDoesntCrash() {
    swipeViewPagerBack();

    assertDisplayed("1");
  }

  @Test
  public void swipingForwardInTheLastPageDoesntCrash() {
    swipeViewPagerForward();
    swipeViewPagerForward();

    assertDisplayed("2");
  }
}
