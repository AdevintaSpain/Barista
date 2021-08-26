package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerBack;
import static com.adevinta.android.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerForward;

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
