package com.schibsted.spain.barista.sample;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertTextColorIs;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertTextColorIsNot;

@RunWith(AndroidJUnit4.class)
public class ColorsTest {

  @Rule
  public ActivityTestRule<ColorsActivity> activityRule = new ActivityTestRule<>(ColorsActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkTextViewTextColor_isExpectedColor() {
    assertTextColorIs(R.id.textViewRed, R.color.textRed);
    assertTextColorIs(R.id.textViewBlue, R.color.textBlue);
  }

  @Test
  public void checkTextViewTextColor_isNotExpectedColor() {
    assertTextColorIsNot(R.id.textViewRed, R.color.textBlue);
    assertTextColorIsNot(R.id.textViewBlue, R.color.textRed);
  }

  @Test
  public void checkTextViewTextColorState_isExpectedColor() {
    assertTextColorIs(R.id.textViewStateRed, R.color.text_state_red);
    assertTextColorIs(R.id.textViewStateBlue, R.color.text_state_blue);
  }

  @Test
  public void checkTextViewTextColorState_isNotExpectedColor() {
    assertTextColorIsNot(R.id.textViewStateRed, R.color.text_state_blue);
    assertTextColorIsNot(R.id.textViewStateBlue, R.color.text_state_red);
  }

  @Test
  public void checkDisabledTextViewTextColorState_isExpectedColor() {
    assertTextColorIs(R.id.textViewStateRedDisabled, R.color.text_state_red);
    assertTextColorIs(R.id.textViewStateBlueDisabled, R.color.text_state_blue);
  }

  @Test
  public void checkDisabledTextViewTextColorState_isExpectedColorSimple() {
    assertTextColorIs(R.id.textViewStateRedDisabled, R.color.colorAccent);
    assertTextColorIs(R.id.textViewStateBlueDisabled, R.color.colorPrimaryDark);
  }

  @Test
  public void checkDisabledTextViewTextColorState_isNotExpectedColor() {
    assertTextColorIsNot(R.id.textViewStateRedDisabled, R.color.text_state_blue);
    assertTextColorIsNot(R.id.textViewStateBlueDisabled, R.color.text_state_red);
  }
}