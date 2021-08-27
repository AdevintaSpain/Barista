package com.adevinta.android.barista.sample;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.PerformException;
import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.internal.failurehandler.BaristaException;
import com.adevinta.android.barista.sample.util.SpyFailureHandlerRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItemChild;
import static com.adevinta.android.barista.sample.ListsActivity.IntentBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ListsChildClickTest {

  @Rule
  public ActivityTestRule<ListsActivity> activity = new ActivityTestRule<>(ListsActivity.class, true, false);

  @Rule
  public SpyFailureHandlerRule spyFailureHandlerRule = new SpyFailureHandlerRule();

  @Test
  public void clickRecyclerItemChild() {
    openActivity(ListsActivity.buildIntent()
        .withRecyclers(R.id.recycler)
    );

    clickListItemChild(20, R.id.yes);

    assertResult("yes");
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void clickListViewItemChild() {
    openActivity(ListsActivity.buildIntent()
        .withComplexLists(R.id.listview)
    );

    clickListItemChild(20, R.id.yes);

    assertResult("yes");
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void clickGridViewItemChild() {
    openActivity(ListsActivity.buildIntent()
        .withComplexGrids(R.id.gridview)
    );

    clickListItemChild(20, R.id.yes);

    assertResult("yes");
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void clickMultipleRecyclerItemChild_byId() {
    openActivity(ListsActivity.buildIntent()
        .withRecyclers(R.id.recycler, R.id.recycler2)
    );

    clickListItemChild(R.id.recycler, 20, R.id.yes);

    assertResult("yes");
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void clickMultipleListViewItemChild_byId() {
    openActivity(ListsActivity.buildIntent()
        .withComplexLists(R.id.listview, R.id.listview2)
    );

    clickListItemChild(R.id.listview, 20, R.id.yes);

    assertResult("yes");
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void clickMultipleGridViewItemChild_byId() {
    openActivity(ListsActivity.buildIntent()
        .withComplexGrids(R.id.gridview, R.id.gridview2)
    );

    clickListItemChild(R.id.gridview, 20, R.id.yes);

    assertResult("yes");
    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void fails_whenRecyclerChildNotExist() {
    openActivity(ListsActivity.buildIntent()
        .withRecyclers(R.id.recycler)
    );

    Throwable thrown = catchThrowable(() -> clickListItemChild(20, R.id.not_exists));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageContaining("Could not perform action (actionOnItemAtPosition performing ViewAction: Click on a child view ")
        .hasMessageContaining("on item at position: 20) on RecyclerView")
        .hasCauseInstanceOf(PerformException.class)
        .hasStackTraceContaining("Didn't find any view view.getId()");
  }

  @Test
  public void fails_whenListViewChildNotExist() {
    openActivity(ListsActivity.buildIntent()
        .withComplexLists(R.id.listview)
    );

    Throwable thrown = catchThrowable(() -> clickListItemChild(20, R.id.not_exists));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageContaining("Could not perform action (Click on a child view ")
        .hasMessageContaining("on ListView")
        .hasCauseInstanceOf(PerformException.class);
  }

  private void openActivity(IntentBuilder intentBuilder) {
    activity.launchActivity(intentBuilder.build(ApplicationProvider.getApplicationContext()));
  }

  private void assertResult(String text) {
    onView(withId(R.id.clicked_text_result)).check(matches(withText(text)));
  }
}
