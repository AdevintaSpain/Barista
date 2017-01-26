package com.schibsted.spain.barista.sample;

import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.rule.TenRepetitionsActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaSwipeActions.swipePagerBack;
import static com.schibsted.spain.barista.BaristaSwipeActions.swipePagerForward;

@RunWith(AndroidJUnit4.class)
public class ViewPagerTest {

  @Rule
  public TenRepetitionsActivityTestRule<ViewPagerActivity> activityRule = new TenRepetitionsActivityTestRule<>(ViewPagerActivity.class);

  @Test
  public void checkSwipeForward() {
    swipePagerForward(R.id.pager);

    assertDisplayed("2");
  }

  @Test
  public void checkSwipeBack() {
    swipePagerForward(R.id.pager);
    swipePagerBack(R.id.pager);

    assertDisplayed("1");
  }

  @Test
  public void swipingBackInTheFirstPageDoesntCrash() {
    swipePagerBack(R.id.pager);

    assertDisplayed("1");
  }

  @Test
  public void swipingForwardInTheLastPageDoesntCrash() {
    swipePagerForward(R.id.pager);
    swipePagerForward(R.id.pager);

    assertDisplayed("2");
  }
}
