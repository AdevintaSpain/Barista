package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;
import static com.schibsted.spain.barista.BaristaRadioButtonActions.clickRadioButtonItem;

@RunWith(AndroidJUnit4.class)
public class RadioButtonsTest {

  @Rule
  public ActivityTestRule<RadioButtonsActivity> activityRule = new ActivityTestRule<>(RadioButtonsActivity.class);

  @Test
  public void checkRadioButtonsById_firstItem() {
    clickRadioButtonItem(R.id.radiogroup, R.id.first_item);
    assertTextIsDisplayed("" + R.id.first_item);
  }

  @Test
  public void checkRadioButtonsById_secondItem() {
    clickRadioButtonItem(R.id.radiogroup, R.id.second_item);
    assertTextIsDisplayed("" + R.id.second_item);
  }

  @Test
  public void checkRadioButtonsByText_firstItem() {
    clickRadioButtonItem(R.id.radiogroup, "Hello");
    assertTextIsDisplayed("" + R.id.first_item);
  }

  @Test
  public void checkRadioButtonsByText_secondItem() {
    clickRadioButtonItem(R.id.radiogroup, "Bye bye");
    assertTextIsDisplayed("" + R.id.second_item);
  }
}