package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn;
import static com.adevinta.android.barista.interaction.BaristaPickerInteractions.setDateOnPicker;
import static com.adevinta.android.barista.interaction.BaristaPickerInteractions.setTimeOnPicker;


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
