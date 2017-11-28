package com.schibsted.spain.barista.sample;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static

        com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotTextColor;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertTextColor;

@RunWith(AndroidJUnit4.class)
public class ColorsTest {

  @Rule
  public ActivityTestRule<ColorsActivity> activityRule = new ActivityTestRule<>(ColorsActivity.
          class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkTextViewTextColor_isExpectedColor() {
    assertTextColor(R.id.textViewRed, R.color.textRed);
    assertTextColor(R.id.textViewBlue, R.color.textBlue);
  }

  @Test
  public void checkTextViewTextColor_isNotExpectedColor() {
    assertNotTextColor(R.id.textViewRed, R.color.textBlue);
    assertNotTextColor(R.id.textViewBlue, R.color.textRed);
  }

  @Test
  public void checkTextViewTextColorState_isExpectedColor() {
    assertTextColor(R.id.textViewStateRed, R.color.text_state_red);
    assertTextColor(R.id.textViewStateBlue, R.color.text_state_blue);
  }

  @Test
  public void checkTextViewTextColorState_isNotExpectedColor() {
    assertNotTextColor(R.id.textViewStateRed, R.color.text_state_blue);
    assertNotTextColor(R.id.textViewStateBlue, R.color.text_state_red);
  }

  @Test
  public void checkDisabledTextViewTextColorState_isExpectedColor() {
    assertTextColor(R.id.textViewStateRed, R.color.text_state_red);
    assertTextColor(R.id.textViewStateBlue, R.color.text_state_blue);
  }

  @Test
  public void checkDisabledTextViewTextColorState_isNotExpectedColor() {
    assertNotTextColor(R.id.textViewStateRed, R.color.text_state_blue);
    assertNotTextColor(R.id.textViewStateBlue, R.color.text_state_red);
  }
}