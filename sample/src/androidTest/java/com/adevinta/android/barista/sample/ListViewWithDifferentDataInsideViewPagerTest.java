package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.adevinta.android.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition;
import static com.adevinta.android.barista.interaction.BaristaSleepInteractions.sleep;
import static com.adevinta.android.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerBack;
import static com.adevinta.android.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerForward;


public class ListViewWithDifferentDataInsideViewPagerTest {

  @Rule
  public ActivityTestRule<ListViewsWithDifferentDataInsideViewPagerActivity> activityRule =
      new ActivityTestRule<>(ListViewsWithDifferentDataInsideViewPagerActivity.class);

  @Test
  public void checkClickRecyclerViewItem() {
    swipeViewPagerForward(R.id.pager);
    sleep(500);

    assertDisplayedAtPosition(R.id.listview, 1, "Marionberry");

    swipeViewPagerBack(R.id.pager);
    sleep(500);

    assertDisplayedAtPosition(R.id.listview, 0, android.R.id.text1, "Apple");
  }
}