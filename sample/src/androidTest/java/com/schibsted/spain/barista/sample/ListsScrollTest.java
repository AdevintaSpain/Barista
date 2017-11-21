package com.schibsted.spain.barista.sample;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition;
import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertNotExist;
import static com.schibsted.spain.barista.sample.ListsActivity.FRUITS;
import static com.schibsted.spain.barista.sample.ListsActivity.IntentBuilder;

@RunWith(AndroidJUnit4.class)
public class ListsScrollTest {

  @Rule
  public ActivityTestRule<ListsActivity> activity = new ActivityTestRule<>(ListsActivity.class, true, false);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void scrollListView_withoutId() throws Exception {
    openActivity(new IntentBuilder()
        .withSimpleLists(R.id.listview)
    );
    assertNotExist(FRUITS[30]);

    scrollListToPosition(30);

    assertDisplayed(FRUITS[30]);
  }

  @Test
  public void scrollRecyclerView_withoutId() throws Exception {
    openActivity(new IntentBuilder()
        .withRecyclers(R.id.recycler)
    );
    assertNotExist(FRUITS[30]);

    scrollListToPosition(30);

    assertDisplayed(FRUITS[30]);
  }

  @Test
  public void scrollMultipleListView_byId() throws Exception {
    openActivity(new IntentBuilder()
        .withSimpleLists(R.id.listview, R.id.listview2)
    );
    assertNotExist(FRUITS[30]);

    scrollListToPosition(R.id.listview, 30);

    assertDisplayed(FRUITS[30]);
  }


  @Test
  public void scrollMultipleRecyclerView_byId() throws Exception {
    openActivity(new IntentBuilder()
        .withRecyclers(R.id.recycler, R.id.recycler2)
    );
    assertNotExist(FRUITS[30]);

    scrollListToPosition(R.id.recycler2, 30);

    assertDisplayed(FRUITS[30]);
  }

  private void openActivity(IntentBuilder intentBuilder) {
    activity.launchActivity(intentBuilder.build(InstrumentationRegistry.getTargetContext()));
  }
}
