package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaRecyclerViewActions.clickRecyclerViewItem;
import static com.schibsted.spain.barista.BaristaRecyclerViewActions.clickRecyclerViewItemChild;
import static com.schibsted.spain.barista.BaristaRecyclerViewActions.scrollTo;

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
    clickRecyclerViewItem(R.id.recycler, POSITION_AT_LAST_PAGE);
    assertDisplayed("Papaya has been clicked");
  }

  @Test
  public void checkScrollToRecyclerViewItem() {
    scrollTo(R.id.recycler, POSITION_AT_LAST_PAGE);
    assertDisplayed("Papaya");
  }

  @Test
  public void checkClickItemChild_childItemByText() {
    clickRecyclerViewItemChild(R.id.recycler, POSITION_AT_LAST_PAGE, "No");
    assertDisplayed("'no' has been clicked");
  }

  @Test
  public void checkClickItemChild_childItemByPosition() {
    clickRecyclerViewItemChild(R.id.recycler, POSITION_AT_LAST_PAGE, R.id.no);
    assertDisplayed("'no' has been clicked");
  }
}
