package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.BaristaCheckBoxActions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;

@RunWith(AndroidJUnit4.class)
public class CheckBoxTest {

  @Rule
  public ActivityTestRule<CheckBoxActivity> activityRule = new ActivityTestRule<>(CheckBoxActivity.class);

  @Test
  public void checkCheckBoxClickById_firstItem() {
    BaristaCheckBoxActions.clickCheckBoxItem(R.id.first_item);
    assertTextIsDisplayed("" + R.id.first_item);
  }

  @Test
  public void checkCheckBoxClickById_secondItem() {
    BaristaCheckBoxActions.clickCheckBoxItem(R.id.second_item);
    assertTextIsDisplayed("" + R.id.second_item);
  }

  @Test
  public void checkCheckBoxClickByText_firstItem() {
    BaristaCheckBoxActions.clickCheckBoxItem("Hello");
    assertTextIsDisplayed("" + R.id.first_item);
  }

  @Test
  public void checkCheckBoxClickByText_secondItem() {
    BaristaCheckBoxActions.clickCheckBoxItem("Bye bye");
    assertTextIsDisplayed("" + R.id.second_item);
  }
}