package com.schibsted.spain.barista.sample;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.internal.failurehandler.BaristaException;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem;
import static com.schibsted.spain.barista.sample.ListsActivity.getComplexListViewTextAt;
import static com.schibsted.spain.barista.sample.ListsActivity.getRecyclerViewTextAt;
import static com.schibsted.spain.barista.sample.ListsActivity.getSimpleListViewTextAt;

@RunWith(AndroidJUnit4.class)
public class ListsClickTest {

  @Rule
  public ActivityTestRule<ListsActivity> activity = new ActivityTestRule<>(ListsActivity.class, true, false);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void clickRecyclerPosition() throws Exception {
    launchTestActivity(ListsActivity.buildIntent()
        .withRecyclers(R.id.recycler)
    );

    clickListItem(18);

    assertResult(getRecyclerViewTextAt(18));
  }

  @Test
  public void clickRecyclerPosition_byId() throws Exception {
    launchTestActivity(ListsActivity.buildIntent()
        .withRecyclers(R.id.recycler)
    );

    clickListItem(R.id.recycler, 15);

    assertResult(getRecyclerViewTextAt(15));
  }

  @Test
  public void clickSimpleListView() throws Exception {
    launchTestActivity(ListsActivity.buildIntent()
        .withSimpleLists(R.id.listview)
    );

    clickListItem(20);

    assertResult(getSimpleListViewTextAt(20));
  }

  @Test
  public void clickComplexListView() throws Exception {
    launchTestActivity(ListsActivity.buildIntent()
        .withComplexLists(R.id.listview)
    );

    clickListItem(20);

    assertResult(getComplexListViewTextAt(20));
  }

  @Test
  public void clickMultipleListView_byId() throws Exception {
    launchTestActivity(ListsActivity.buildIntent()
        .withSimpleLists(R.id.listview, R.id.listview2)
    );
    clickListItem(R.id.listview, 20);

    assertResult(getSimpleListViewTextAt(20));
  }

  @Test
  public void clickSimpleGridView() throws Exception {
    launchTestActivity(ListsActivity.buildIntent()
            .withSimpleGrids(R.id.gridview)
    );

    clickListItem(20);

    assertResult(getSimpleListViewTextAt(20));
  }

  @Test
  public void clickComplexGridView() throws Exception {
    launchTestActivity(ListsActivity.buildIntent()
            .withComplexGrids(R.id.gridview)
    );

    clickListItem(20);

    assertResult(getComplexListViewTextAt(20));
  }

  @Test
  public void clickMultipleGridView_byId() throws Exception {
    launchTestActivity(ListsActivity.buildIntent()
            .withSimpleLists(R.id.gridview, R.id.gridview2)
    );
    clickListItem(R.id.gridview, 20);

    assertResult(getSimpleListViewTextAt(20));
  }


  @Test(expected = BaristaException.class)
  public void fail_whenNoViewFound() throws Exception {
    launchTestActivity(ListsActivity.buildIntent());

    clickListItem(20);
  }

  @Test(expected = BaristaException.class)
  public void fail_whenNoViewFound_byId() throws Exception {
    launchTestActivity(ListsActivity.buildIntent()
        .withSimpleLists(R.id.listview)
    );

    clickListItem(R.id.listview2, 20);
  }

  @Test(expected = BaristaException.class)
  public void fail_whenMultipleListsViews_withoutId() throws Exception {
    launchTestActivity(ListsActivity.buildIntent()
        .withSimpleLists(R.id.listview, R.id.listview2)
    );

    clickListItem(20);
  }

  @Test(expected = BaristaException.class)
  public void fail_whenMultipleGridViews_withoutId() throws Exception {
    launchTestActivity(ListsActivity.buildIntent()
            .withSimpleGrids(R.id.gridview, R.id.gridview2)
    );

    clickListItem(20);
  }

  @Test(expected = BaristaException.class)
  public void fail_whenRecyclerAndListView_withoutId() throws Exception {
    launchTestActivity(ListsActivity.buildIntent()
        .withRecyclers(R.id.recycler)
        .withSimpleLists(R.id.listview)
        .withSimpleGrids(R.id.gridview)
    );

    clickListItem(20);
  }

  private void assertResult(String text) {
    onView(withId(R.id.clicked_text_result)).check(matches(withText(text)));
  }

  private void launchTestActivity(ListsActivity.IntentBuilder intentBuilder) {
    activity.launchActivity(intentBuilder.build(InstrumentationRegistry.getTargetContext()));
  }
}
