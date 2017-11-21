package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerBack;
import static com.schibsted.spain.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerForward;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;

@RunWith(AndroidJUnit4.class)
public class ViewPagerTest {

  @Rule
  public ActivityTestRule<ViewPagerWithTwoDifferentPagesActivity> activityRule =
      new ActivityTestRule<>(ViewPagerWithTwoDifferentPagesActivity.class);

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
