package com.schibsted.spain.barista.sample;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.interaction.BaristaListInteractions.assertDisplayedAtPosition;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.assertListItemCount;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewAssertionTest {

  @Rule
  public ActivityTestRule<ListsActivity> activityTestRule = new ActivityTestRule<>(ListsActivity.class, true, false);

  @Before
  public void setup() {
    openActivity(ListsActivity.buildIntent().withRecyclers(R.id.recycler));
  }

  @Test
  public void shouldHaveExpectedNumberOfEntriesInRecyclerView() {
    int expectedListLength = ListsActivity.FRUITS.length;
    assertListItemCount(R.id.recycler, expectedListLength);
  }

  @Test
  public void shouldFindItemInRecyclerViewWithoutId() {
    assertDisplayedAtPosition(R.id.recycler, 2, "Avocado");
  }

  @Test
  public void shouldFindItemInRecyclerViewWithId() {
    assertDisplayedAtPosition(R.id.recycler, 4, R.id.textview, "Bilberry");
  }

  @Test(expected = AssertionFailedError.class)
  public void shouldFailWhenUnableToFindItemInRecyclerView() {
    assertDisplayedAtPosition(R.id.recycler, 2, "Missing");
  }

  private void openActivity(ListsActivity.IntentBuilder intentBuilder) {
    activityTestRule.launchActivity(intentBuilder.build(InstrumentationRegistry.getTargetContext()));
  }
}