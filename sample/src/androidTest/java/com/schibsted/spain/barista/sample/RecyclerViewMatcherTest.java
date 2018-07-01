package com.schibsted.spain.barista.sample;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertDisplayedAtPosition;
import static com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewMatcherTest {

  @Rule
  public ActivityTestRule<ListsActivity> activityTestRule = new ActivityTestRule<>(ListsActivity.class, true, false);

  @Before
  public void setup() {
    openActivity(ListsActivity.buildIntent()
      .withRecyclers(R.id.recycler)
    );
  }

  @Test
  public void shouldHaveFourEntriesInRecyclerView() {
    assertRecyclerViewItemCount(R.id.recycler, 89);
  }

  @Test
  public void shouldFindItemInRecyclerViewWithoutId() {
    assertDisplayedAtPosition(R.id.recycler, 2, "Avocado");
  }

  @Test
  public void shouldFindItemInRecyclerViewWithId() {
    assertDisplayedAtPosition(R.id.recycler, 4, R.id.textview, "Bilberry");
  }

  private void openActivity(ListsActivity.IntentBuilder intentBuilder) {
    activityTestRule.launchActivity(intentBuilder.build(InstrumentationRegistry.getTargetContext()));
  }
}