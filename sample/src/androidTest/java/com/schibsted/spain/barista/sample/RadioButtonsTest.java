package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaRadioButtonActions.clickRadioButtonItem;
import static com.schibsted.spain.barista.BaristaRadioButtonActions.clickRadioButtonPosition;

@RunWith(AndroidJUnit4.class)
public class RadioButtonsTest {

  @Rule
  public ActivityTestRule<RadioButtonsActivity> activityRule = new ActivityTestRule<>(RadioButtonsActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkRadioButtonsById_firstItem() {
    clickRadioButtonItem(R.id.radiogroup, R.id.first_item);
    assertDisplayed("" + R.id.first_item);
  }

  @Test
  public void checkRadioButtonsById_secondItem() {
    clickRadioButtonItem(R.id.radiogroup, R.id.second_item);
    assertDisplayed("" + R.id.second_item);
  }

  @Test
  public void checkRadioButtonsByText_firstItem() {
    clickRadioButtonItem(R.id.radiogroup, "Hello");
    assertDisplayed("" + R.id.first_item);
  }

  @Test
  public void checkRadioButtonsByText_secondItem() {
    clickRadioButtonItem(R.id.radiogroup, "Bye bye");
    assertDisplayed("" + R.id.second_item);
  }

  @Test
  public void checkRadioButtonsByPosition_firstItem() {
    clickRadioButtonPosition(R.id.radiogroup, 0);
    assertDisplayed("" + R.id.first_item);
  }

  @Test
  public void checkRadioButtonsByPosition_secondItem() {
    clickRadioButtonPosition(R.id.radiogroup, 1);
    assertDisplayed("" + R.id.second_item);
  }
}