package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;
import static com.schibsted.spain.barista.BaristaRecyclerViewActions.clickRecyclerViewItem;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

  @Rule
  public ActivityTestRule<RecyclerViewActivity> activityRule = new ActivityTestRule<>(RecyclerViewActivity.class);

  @Test
  public void checkClickRecyclerViewItem_byPosition_atTwo() {
    clickRecyclerViewItem(R.id.recycler, 2);
    assertTextIsDisplayed("Orange");
  }

  @Test
  public void checkClickRecyclerViewItem_byPosition_atThree() {
    clickRecyclerViewItem(R.id.recycler, 3);
    assertTextIsDisplayed("Raspberry");
  }
}
