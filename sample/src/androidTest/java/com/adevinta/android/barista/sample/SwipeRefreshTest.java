package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaSwipeRefreshInteractions.refresh;

public class SwipeRefreshTest {

  @Rule public ActivityTestRule<SwipeRefreshActivity> activityRule =
      new ActivityTestRule<>(SwipeRefreshActivity.class);

  @Test
  public void checkSwipeRefresh_isRefreshing() {
    refresh(R.id.swiperefresh);
    assertDisplayed("I am refreshing!");
  }

  @Test
  public void checkSwipeRefresh_isRefreshing_withoutPassingTheIdParameter() {
    refresh();
    assertDisplayed("I am refreshing!");
  }
}
