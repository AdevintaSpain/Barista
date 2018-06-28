package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;
import static com.schibsted.spain.barista.interaction.BaristaPickerInteractions.setDateOnPicker;
import static com.schibsted.spain.barista.interaction.BaristaPickerInteractions.setTimeOnPicker;

@RunWith(AndroidJUnit4.class)
public class PickersActivityTest {

  @Rule
  public ActivityTestRule<PickersActivity> activityRule = new ActivityTestRule<>(PickersActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkDatePicker() {
    clickOn(R.id.launch_date_picker);
    setDateOnPicker(1986, 04, 23);
    assertDisplayed("1986+3+23");
  }

  @Test
  public void checkTimePicker() {
    clickOn(R.id.launch_time_picker);
    setTimeOnPicker(13, 46);
    assertDisplayed("13:46");
  }
}
