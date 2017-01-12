package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;
import static com.schibsted.spain.barista.BaristaClickActions.click;
import static com.schibsted.spain.barista.BaristaPickerActions.setDateOnPicker;

@RunWith(AndroidJUnit4.class)
public class DatePickerTest {

  @Rule
  public ActivityTestRule<DatePickerActivity> activityRule = new ActivityTestRule<>(DatePickerActivity.class);

  @Test
  public void checkDatePicker() {
    click(R.id.launch_date_picker);
    setDateOnPicker(1986, 04, 23);
    assertTextIsDisplayed("1986+3+23");
  }
}
