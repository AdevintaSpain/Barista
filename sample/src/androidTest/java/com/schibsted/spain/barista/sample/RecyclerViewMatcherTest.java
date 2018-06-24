package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertDisplayedAtPosition;
import static com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewMatcherTest {

  @Rule
  public ActivityTestRule<RecyclerViewActivity> activityRule = new ActivityTestRule<>(RecyclerViewActivity.class);

  @Test
  public void shouldHaveFourEntriesInRecyclerView() {
    assertRecyclerViewItemCount(R.id.recycler, 4);
  }

  @Test
  public void shouldFindItemInRecyclerViewWithoutId() {
    assertDisplayedAtPosition(R.id.recycler, 1, "Apricot");
  }

  @Test
  public void shouldFindItemInRecyclerViewWithId() {
    assertDisplayedAtPosition(R.id.recycler, 3, android.R.id.text1, "Banana");
  }
}
