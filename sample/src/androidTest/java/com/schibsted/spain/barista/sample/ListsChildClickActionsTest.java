package com.schibsted.spain.barista.sample;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.PerformException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.action.BaristaListActions.clickListItemChild;
import static com.schibsted.spain.barista.sample.ListsActivity.IntentBuilder;

@RunWith(AndroidJUnit4.class)
public class ListsChildClickActionsTest {

  @Rule
  public ActivityTestRule<ListsActivity> activity = new ActivityTestRule<>(ListsActivity.class, true, false);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void clickRecyclerItemChild() throws Exception {
    openActivity(ListsActivity.buildIntent()
        .withRecyclers(R.id.recycler)
    );

    clickListItemChild(20, R.id.yes);

    assertResult("yes");
  }

  @Test
  public void clickListViewItemChild() throws Exception {
    openActivity(ListsActivity.buildIntent()
        .withComplexLists(R.id.listview)
    );

    clickListItemChild(20, R.id.yes);

    assertResult("yes");
  }

  @Test
  public void clickMultipleRecyclerItemChild_byId() throws Exception {
    openActivity(ListsActivity.buildIntent()
        .withRecyclers(R.id.recycler, R.id.recycler2)
    );

    clickListItemChild(R.id.recycler, 20, R.id.yes);

    assertResult("yes");
  }

  @Test
  public void clickMultipleListViewItemChild_byId() throws Exception {
    openActivity(ListsActivity.buildIntent()
        .withComplexLists(R.id.listview, R.id.listview2)
    );

    clickListItemChild(R.id.listview, 20, R.id.yes);

    assertResult("yes");
  }

  @Test(expected = PerformException.class)
  public void fails_whenListViewChildNotExist() throws Exception {
    openActivity(ListsActivity.buildIntent()
        .withComplexLists(R.id.listview)
    );

    clickListItemChild(20, R.id.unknown);

    assertResult("");
  }

  private void openActivity(IntentBuilder intentBuilder) {
    activity.launchActivity(intentBuilder.build(InstrumentationRegistry.getTargetContext()));
  }

  private void assertResult(String text) {
    onView(withId(R.id.clicked_text_result)).check(matches(withText(text)));
  }
}
