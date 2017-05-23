package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaSwipeRefreshActions.pullToRefresh;

@RunWith(AndroidJUnit4.class) public class SwipeRefreshTest {

  @Rule public ActivityTestRule<SwipeRefreshActivity> activityRule =
      new ActivityTestRule<>(SwipeRefreshActivity.class);

  @Test
  public void testSwipeRefresh_isRefreshing() {
    pullToRefresh(R.id.swiperefresh);
    assertDisplayed("I am refreshing!");
  }
}
