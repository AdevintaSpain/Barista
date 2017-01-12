package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;
import static com.schibsted.spain.barista.BaristaSwipeActions.swipePagerBack;
import static com.schibsted.spain.barista.BaristaSwipeActions.swipePagerForward;

@RunWith(AndroidJUnit4.class)
public class ViewPagerTest {

  @Rule
  public ActivityTestRule<ViewPagerActivity> activityRule = new ActivityTestRule<>(ViewPagerActivity.class);

  @Test
  public void checkSwipeForward() {
    swipePagerForward(R.id.pager);

    assertTextIsDisplayed("2");
  }

  @Test
  public void checkSwipeBack() {
    swipePagerForward(R.id.pager);
    swipePagerBack(R.id.pager);

    assertTextIsDisplayed("1");
  }

  @Test
  public void swipingBackInTheFirstPageDoesntCrash() {
    swipePagerBack(R.id.pager);

    assertTextIsDisplayed("1");
  }

  @Test
  public void swipingForwardInTheLastPageDoesntCrash() {
    swipePagerForward(R.id.pager);
    swipePagerForward(R.id.pager);

    assertTextIsDisplayed("2");
  }
}
