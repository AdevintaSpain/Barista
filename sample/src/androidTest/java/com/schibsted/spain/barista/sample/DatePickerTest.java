package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.cleardata.MonitorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaClickActions.click;
import static com.schibsted.spain.barista.BaristaPickerActions.setDateOnPicker;

@RunWith(AndroidJUnit4.class)
public class DatePickerTest {

  @Rule
  public MonitorRule monitorRule = new MonitorRule();

  @Rule
  public ActivityTestRule<DatePickerActivity> activityRule = new ActivityTestRule<>(DatePickerActivity.class);

  @Test
  public void checkDatePicker() {
    click(R.id.launch_date_picker);
    setDateOnPicker(1986, 04, 23);
    assertDisplayed("1986+3+23");
  }
}
