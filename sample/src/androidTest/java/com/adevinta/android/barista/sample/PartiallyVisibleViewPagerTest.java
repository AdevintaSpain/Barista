package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.adevinta.android.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerBack;
import static com.adevinta.android.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerForward;


public class PartiallyVisibleViewPagerTest {

  @Rule
  public ActivityTestRule<PartiallyVisibleViewPagerActivity> activityRule =
      new ActivityTestRule<>(PartiallyVisibleViewPagerActivity.class);

  @Test
  public void checkSwipeForwardDoesNotOpenDrawer() {
    swipeViewPagerForward(R.id.pager);

    assertNotDisplayed(R.id.drawer);
  }

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
