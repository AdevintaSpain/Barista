package com.schibsted.spain.barista.sample;

import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.flakyespresso.FlakyActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaViewPagerActions.swipeViewPagerBack;
import static com.schibsted.spain.barista.BaristaViewPagerActions.swipeViewPagerForward;

@RunWith(AndroidJUnit4.class)
public class ViewPagerWithoutIdTest {

  @Rule
  public FlakyActivityTestRule<ViewPagerWithTwoDifferentPagesActivity> activityRule = new FlakyActivityTestRule<>(ViewPagerWithTwoDifferentPagesActivity.class)
      .allowFlakyAttemptsByDefault(10);

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
