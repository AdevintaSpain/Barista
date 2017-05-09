package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaRecyclerViewActions.clickRecyclerViewItem;
import static com.schibsted.spain.barista.BaristaRecyclerViewActions.clickRecyclerViewItemChild;
import static com.schibsted.spain.barista.BaristaRecyclerViewActions.scrollTo;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewInsideViewPagerTest {

  @Rule
  public ActivityTestRule<RecyclerViewsInsideViewPagerActivity> activityRule = new ActivityTestRule<>(RecyclerViewsInsideViewPagerActivity.class);

  @Test
  public void checkClickRecyclerViewItem_atSixty() {
    int position = 60;
    clickRecyclerViewItem(R.id.recycler, position);
    assertDisplayed("Papaya has been clicked");
  }

  @Test
  public void checkScrollToRecyclerViewItem_atSixty() {
    int position = 60;
    scrollTo(R.id.recycler, position);
    assertDisplayed("Papaya");
  }

  @Test
  public void checkClickNoButton_byPosition_andText_atSixty() {
    int position = 60;
    clickRecyclerViewItemChild(R.id.recycler, position, "No");
    assertDisplayed("'no' has been clicked");
  }

  @Test
  public void checkClickNoButton_byPosition_andResId_atSixty() {
    int position = 60;
    clickRecyclerViewItemChild(R.id.recycler, position, R.id.no);
    assertDisplayed("'no' has been clicked");
  }
}
