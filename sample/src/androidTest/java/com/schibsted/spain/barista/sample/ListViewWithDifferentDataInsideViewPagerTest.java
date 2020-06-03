package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition;
import static com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep;
import static com.schibsted.spain.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerBack;
import static com.schibsted.spain.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerForward;

@RunWith(AndroidJUnit4.class)
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