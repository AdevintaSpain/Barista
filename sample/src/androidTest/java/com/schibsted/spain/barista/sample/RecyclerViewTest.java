package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertRecyclerViewItemCount;
import static com.schibsted.spain.barista.BaristaRecyclerViewActions.clickRecyclerViewItem;
import static com.schibsted.spain.barista.BaristaRecyclerViewActions.clickRecyclerViewItemChild;
import static com.schibsted.spain.barista.BaristaRecyclerViewActions.scrollTo;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

  @Rule
  public ActivityTestRule<RecyclerViewActivity> activityRule = new ActivityTestRule<>(RecyclerViewActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkClickRecyclerViewItem_byPosition_atTwo() {
    clickRecyclerViewItem(R.id.recycler, 2);
    assertDisplayed("Avocado has been clicked");
  }

  //region Clicks
  @Test
  public void checkClickRecyclerViewItem_byPosition_atThree() {
    clickRecyclerViewItem(R.id.recycler, 3);
    assertDisplayed("Banana has been clicked");
  }

  @Test
  public void checkClickRecyclerViewItem_byPosition_atTwenty() {
    clickRecyclerViewItem(R.id.recycler, 20);
    assertDisplayed("Durian has been clicked");
  }

  @Test
  public void checkClickRecyclerViewItem_byPosition_atFourty() {
    clickRecyclerViewItem(R.id.recycler, 40);
    assertDisplayed("Lime has been clicked");
  }

  @Test
  public void checkClickRecyclerViewItem_byPosition_atSixty() {
    clickRecyclerViewItem(R.id.recycler, 60);
    assertDisplayed("Papaya has been clicked");
  }
  //endregion

  //region Scrolls
  @Test
  public void checkScrollToRecyclerViewItem_byPosition_atZero() {
    scrollTo(R.id.recycler, 0);
    assertDisplayed("Apple");
  }

  @Test
  public void checkScrollToRecyclerViewItem_byPosition_atTwenty() {
    scrollTo(R.id.recycler, 20);
    assertDisplayed("Durian");
  }

  @Test
  public void checkScrollToRecyclerViewItem_byPosition_atFourty() {
    scrollTo(R.id.recycler, 40);
    assertDisplayed("Lime");
  }

  @Test
  public void checkScrollToRecyclerViewItem_byPosition_atSixty() {
    scrollTo(R.id.recycler, 60);
    assertDisplayed("Papaya");
  }
  //endregion

  //region Clicking row buttons
  @Test
  public void checkClickYesButton_byPosition_atZero() {
    int position = 0;
    clickRecyclerViewItemChild(R.id.recycler, position, R.id.yes);
    assertDisplayed("'yes' has been clicked");
  }

  @Test
  public void checkClickYesButton_byPosition_atFourty() {
    int position = 40;
    clickRecyclerViewItemChild(R.id.recycler, position, R.id.yes);
    assertDisplayed("'yes' has been clicked");
  }

  @Test
  public void checkClickYesButton_byPosition_atSixty() {
    int position = 60;
    clickRecyclerViewItemChild(R.id.recycler, position, R.id.yes);
    assertDisplayed("'yes' has been clicked");
  }

  @Test
  public void checkClickNoButton_byPosition_atZero() {
    int position = 0;
    clickRecyclerViewItemChild(R.id.recycler, position, R.id.no);
    assertDisplayed("'no' has been clicked");
  }

  @Test
  public void checkClickNoButton_byPosition_atFourty() {
    int position = 40;
    clickRecyclerViewItemChild(R.id.recycler, position, R.id.no);
    assertDisplayed("'no' has been clicked");
  }

  @Test
  public void checkClickNoButton_byPosition_atSixty() {
    int position = 60;
    clickRecyclerViewItemChild(R.id.recycler, position, R.id.no);
    assertDisplayed("'no' has been clicked");
  }

  @Test
  public void checkClickYesButton_byPosition_atZero_byText() {
    int position = 0;
    clickRecyclerViewItemChild(R.id.recycler, position, "Yes");
    assertDisplayed("'yes' has been clicked");
  }

  @Test
  public void checkClickYesButton_byPosition_atFourty_byText() {
    int position = 40;
    clickRecyclerViewItemChild(R.id.recycler, position, "Yes");
    assertDisplayed("'yes' has been clicked");
  }

  @Test
  public void checkClickYesButton_byPosition_atSixty_byText() {
    int position = 60;
    clickRecyclerViewItemChild(R.id.recycler, position, "Yes");
    assertDisplayed("'yes' has been clicked");
  }

  @Test
  public void checkClickNoButton_byPosition_atZero_byText() {
    int position = 0;
    clickRecyclerViewItemChild(R.id.recycler, position, "No");
    assertDisplayed("'no' has been clicked");
  }

  @Test
  public void checkClickNoButton_byPosition_atFourty_byText() {
    int position = 40;
    clickRecyclerViewItemChild(R.id.recycler, position, "No");
    assertDisplayed("'no' has been clicked");
  }

  @Test
  public void checkClickNoButton_byPosition_atSixty_byText() {
    int position = 60;
    clickRecyclerViewItemChild(R.id.recycler, position, "No");
    assertDisplayed("'no' has been clicked");
  }
  //endregion

  //region Item count
  @Test
  public void assertItemCount_byDataSetCount() {
    int expectedCount = RecyclerViewActivity.DATA_COUNT;
    assertRecyclerViewItemCount(R.id.recycler, expectedCount);
  }
  //endRegion
}
