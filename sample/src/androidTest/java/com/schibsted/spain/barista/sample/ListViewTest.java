package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaListViewActions.clickListViewItem;

@RunWith(AndroidJUnit4.class)
public class ListViewTest {

  @Rule
  public ActivityTestRule<ListViewActivity> activityRule = new ActivityTestRule<>(ListViewActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkClickListViewItem_withFirstItem_withoutExplicitViewModel() {
    clickListViewItem(R.id.listview, 0);
    assertDisplayed("Banana");
  }

  @Test
  public void checkClickListViewItem_withFourthItem_withoutExplicitViewModel() {
    clickListViewItem(R.id.listview, 3);
    assertDisplayed("Raspberry");
  }

  @Test
  public void checkClickListViewItem_withFirstItem() {
    clickListViewItem(R.id.listview, String.class, 0);
    assertDisplayed("Banana");
  }

  @Test
  public void checkClickListViewItem_withFourthItem() {
    clickListViewItem(R.id.listview, String.class, 3);
    assertDisplayed("Raspberry");
  }
}
