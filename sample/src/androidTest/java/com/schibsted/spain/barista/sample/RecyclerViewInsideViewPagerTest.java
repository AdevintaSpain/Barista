package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItemChild;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewInsideViewPagerTest {

  private static int POSITION_AT_LAST_PAGE = 60;

  @Rule
  public ActivityTestRule<RecyclerViewsInsideViewPagerActivity> activityRule =
      new ActivityTestRule<>(RecyclerViewsInsideViewPagerActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkClickRecyclerViewItem() {
    clickListItem(R.id.recycler, POSITION_AT_LAST_PAGE);
    assertDisplayed("Papaya has been clicked");
  }

  @Test
  public void checkScrollToRecyclerViewItem() {
    scrollListToPosition(R.id.recycler, POSITION_AT_LAST_PAGE);
    assertDisplayed("Papaya");
  }

  @Test
  public void checkClickItemChild_childItemByPosition() {
    clickListItemChild(R.id.recycler, POSITION_AT_LAST_PAGE, R.id.no);
    assertDisplayed("'no' has been clicked");
  }
}
