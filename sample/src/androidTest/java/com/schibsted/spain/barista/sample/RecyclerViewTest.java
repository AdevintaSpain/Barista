package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaRecyclerViewActions.clickRecyclerViewItem;
import static com.schibsted.spain.barista.BaristaRecyclerViewActions.scrollTo;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

  @Rule
  public ActivityTestRule<RecyclerViewActivity> activityRule = new ActivityTestRule<>(RecyclerViewActivity.class);

  @Test
  public void checkClickRecyclerViewItem_byPosition_atTwo() {
    clickRecyclerViewItem(R.id.recycler, 2);
    assertDisplayed("Avocado");
  }

  //region Clicks
  @Test
  public void checkClickRecyclerViewItem_byPosition_atThree() {
    clickRecyclerViewItem(R.id.recycler, 3);
    assertDisplayed("Banana");
  }

  @Test
  public void checkClickRecyclerViewItem_byPosition_atTwenty() {
    clickRecyclerViewItem(R.id.recycler, 20);
    assertDisplayed("Durian");
  }

  @Test
  public void checkClickRecyclerViewItem_byPosition_atFourty() {
    clickRecyclerViewItem(R.id.recycler, 40);
    assertDisplayed("Lime");
  }

  @Test
  public void checkClickRecyclerViewItem_byPosition_atSixty() {
    clickRecyclerViewItem(R.id.recycler, 60);
    assertDisplayed("Papaya");
  }
  //endregion

  //region Scrolls
  @Test
  public void checkScrollToRecyclerViewItem_byPosition_atZero() {
    scrollTo(R.id.recycler, 0);
    assertDisplayed("Apple");
  }

  @Test
  public void checkScrollToRecyclerViewItem_byPosition_atTwenty() {
    scrollTo(R.id.recycler, 20);
    assertDisplayed("Durian");
  }

  @Test
  public void checkScrollToRecyclerViewItem_byPosition_atFourty() {
    scrollTo(R.id.recycler, 40);
    assertDisplayed("Lime");
  }

  @Test
  public void checkScrollToRecyclerViewItem_byPosition_atSixty() {
    scrollTo(R.id.recycler, 60);
    assertDisplayed("Papaya");
  }
  //endregion
}
