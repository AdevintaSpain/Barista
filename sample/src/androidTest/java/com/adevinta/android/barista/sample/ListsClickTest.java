package com.adevinta.android.barista.sample;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.internal.failurehandler.BaristaException;
import com.adevinta.android.barista.sample.util.SpyFailureHandlerRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem;
import static com.adevinta.android.barista.sample.ListsActivity.getComplexListViewTextAt;
import static com.adevinta.android.barista.sample.ListsActivity.getRecyclerViewTextAt;
import static com.adevinta.android.barista.sample.ListsActivity.getSimpleListViewTextAt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ListsClickTest {

  @Rule
  public ActivityTestRule<ListsActivity> activity = new ActivityTestRule<>(ListsActivity.class, true, false);

  @Rule
  public SpyFailureHandlerRule spyFailureHandlerRule = new SpyFailureHandlerRule();

  @Test
  public void clickRecyclerPosition() {
    launchTestActivity(ListsActivity.buildIntent()
        .withRecyclers(R.id.recycler)
    );

    clickListItem(18);

    assertResult(getRecyclerViewTextAt(18));
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void clickRecyclerPosition_byId() {
    launchTestActivity(ListsActivity.buildIntent()
        .withRecyclers(R.id.recycler)
    );

    clickListItem(R.id.recycler, 15);

    assertResult(getRecyclerViewTextAt(15));
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void clickSimpleListView() {
    launchTestActivity(ListsActivity.buildIntent()
        .withSimpleLists(R.id.listview)
    );

    clickListItem(20);

    assertResult(getSimpleListViewTextAt(20));
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void clickComplexListView() {
    launchTestActivity(ListsActivity.buildIntent()
        .withComplexLists(R.id.listview)
    );

    clickListItem(20);

    assertResult(getComplexListViewTextAt(20));
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void clickMultipleListView_byId() {
    launchTestActivity(ListsActivity.buildIntent()
        .withSimpleLists(R.id.listview, R.id.listview2)
    );
    clickListItem(R.id.listview, 20);

    assertResult(getSimpleListViewTextAt(20));
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void clickSimpleGridView() {
    launchTestActivity(ListsActivity.buildIntent()
        .withSimpleGrids(R.id.gridview)
    );

    clickListItem(20);

    assertResult(getSimpleListViewTextAt(20));
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void clickComplexGridView() {
    launchTestActivity(ListsActivity.buildIntent()
        .withComplexGrids(R.id.gridview)
    );

    clickListItem(20);

    assertResult(getComplexListViewTextAt(20));
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void clickMultipleGridView_byId() {
    launchTestActivity(ListsActivity.buildIntent()
        .withSimpleLists(R.id.gridview, R.id.gridview2)
    );
    clickListItem(R.id.gridview, 20);

    assertResult(getSimpleListViewTextAt(20));
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void fail_whenNoViewFound() {
    launchTestActivity(ListsActivity.buildIntent());

    Throwable thrown = catchThrowable(() -> clickListItem(20));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessage("No ListView or RecyclerView found in the hierarchy");
  }

  @Test
  public void fail_whenNoViewFound_byId() {
    launchTestActivity(ListsActivity.buildIntent()
        .withSimpleLists(R.id.listview)
    );

    Throwable thrown = catchThrowable(() -> clickListItem(R.id.listview2, 20));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageContaining("No ListView or RecyclerView with id")
        .hasMessageContaining("listview2")
        .hasMessageContaining("Did you use a wrong id?");
  }

  @Test
  public void fail_whenMultipleListsViews_withoutId() {
    launchTestActivity(ListsActivity.buildIntent()
        .withSimpleLists(R.id.listview, R.id.listview2)
    );

    Throwable thrown = catchThrowable(() -> clickListItem(20));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessage("There are multiple ListView or RecyclerView in the hierarchy."
            + " You must specify an id parameter using clickListItem(id, position)");
  }

  @Test
  public void fail_whenMultipleGridViews_withoutId() {
    launchTestActivity(ListsActivity.buildIntent()
        .withSimpleGrids(R.id.gridview, R.id.gridview2)
    );

    Throwable thrown = catchThrowable(() -> clickListItem(20));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessage("There are multiple ListView or RecyclerView in the hierarchy. "
            + "You must specify an id parameter using clickListItem(id, position)");
  }

  @Test
  public void fail_whenRecyclerAndListView_withoutId() {
    launchTestActivity(ListsActivity.buildIntent()
        .withRecyclers(R.id.recycler)
        .withSimpleLists(R.id.listview)
        .withSimpleGrids(R.id.gridview)
    );

    Throwable thrown = catchThrowable(() -> clickListItem(20));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessage("There are multiple ListView or RecyclerView in the hierarchy. "
            + "You must specify an id parameter using clickListItem(id, position)");
  }

  @Test
  public void fail_whenWrongRecyclerItemPosition() {
    launchTestActivity(ListsActivity.buildIntent()
        .withRecyclers(R.id.recycler)
    );

    Throwable thrown = catchThrowable(() -> clickListItem(999));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageContaining("Could not perform action")
        .hasMessageContaining("performClick() on item at position: 999")
        .hasMessageContaining("on RecyclerView")
        .hasStackTraceContaining("No view holder at position: 999");
  }

  @Test
  public void fail_whenWrongListViewItemPosition() {
    launchTestActivity(ListsActivity.buildIntent()
        .withSimpleLists(R.id.listview)
    );

    Throwable thrown = catchThrowable(() -> clickListItem(999));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageContaining("Could not perform action (Click on the view using performClick()) on ListView")
        .hasStackTraceContaining("requested 999 element");
  }

  private void assertResult(String text) {
    onView(withId(R.id.clicked_text_result)).check(matches(withText(text)));
  }

  private void launchTestActivity(ListsActivity.IntentBuilder intentBuilder) {
    activity.launchActivity(intentBuilder.build(ApplicationProvider.getApplicationContext()));
  }
}
