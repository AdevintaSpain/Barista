package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem;
import static com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItemChild;
import static com.adevinta.android.barista.interaction.BaristaListInteractions.scrollListToPosition;

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
