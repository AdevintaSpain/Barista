package com.schibsted.spain.barista.sample

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.schibsted.spain.barista.interaction.BaristaListInteractions.assertDisplayedAtPosition
import com.schibsted.spain.barista.interaction.BaristaListInteractions.assertListItemCount
import junit.framework.AssertionFailedError
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListViewAssertionTest {

  @Rule
  @JvmField
  var activityTestRule = ActivityTestRule(ListsActivity::class.java, true, false)

  @Test
  fun shouldHaveExpectedNumberOfEntriesInListView() {
    openSimpleListActivity()
    val expectedListLength = ListsActivity.FRUITS.size
    assertListItemCount(R.id.listview, expectedListLength)
  }

  @Test
  fun shouldFindItemInListViewWithoutIdInSimpleList() {
    openSimpleListActivity()
    assertDisplayedAtPosition(listId = R.id.listview, position = 2, text = "Avocado")
  }

  @Test
  fun shouldFindItemInListViewWithIdInSimpleList() {
    openSimpleListActivity()
    assertDisplayedAtPosition(listId = R.id.listview, position = 4, targetViewId = android.R.id.text1, text = "Bilberry")
  }

  @Test(expected = AssertionFailedError::class)
  fun shouldFailWhenUnableToFindItemInSimpleList() {
    openSimpleListActivity()
    assertDisplayedAtPosition(listId = R.id.listview, position = 2, text = "NotThere")
  }

  @Test
  fun shouldFindItemInListViewWithoutIdInComplexList() {
    openComplexListActivity()
    assertDisplayedAtPosition(R.id.listview, 86, "Tamarind")
  }

  @Test
  fun shouldFindItemInListViewWithIdInComplexList() {
    openComplexListActivity()
    assertDisplayedAtPosition(R.id.listview, 19, R.id.textview, "Dragonfruit")
  }

  @Test(expected = AssertionFailedError::class)
  fun shouldFailWhenUnableToFindItemInComplexList() {
    openComplexListActivity()
    assertDisplayedAtPosition(R.id.listview, 86, "Missing")
  }

  private fun openActivity(intentBuilder: ListsActivity.IntentBuilder) {
    activityTestRule.launchActivity(intentBuilder.build(InstrumentationRegistry.getTargetContext()))
  }

  private fun openSimpleListActivity() {
    openActivity(ListsActivity.buildIntent().withSimpleLists(R.id.listview))
  }

  private fun openComplexListActivity() {
    openActivity(ListsActivity.buildIntent().withComplexLists(R.id.listview))
  }
}