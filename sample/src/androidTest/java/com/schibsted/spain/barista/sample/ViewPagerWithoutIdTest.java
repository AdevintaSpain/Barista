package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerBack;
import static com.schibsted.spain.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerForward;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;

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
