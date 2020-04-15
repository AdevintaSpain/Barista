package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.internal.failurehandler.BaristaException;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertTextColorIs;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertTextColorIsNot;

@RunWith(AndroidJUnit4.class)
public class ColorsSecondaryTest {

  @Rule
  public ActivityTestRule<ColorsSecondActivity> activityRule = new ActivityTestRule<>(ColorsSecondActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkColorStyleable_colorStateList() {
    assertTextColorIs(R.id.customTextView, R.styleable.SampleCustomView, R.style.SampleCustomStyle_ColorState, R.styleable.SampleCustomView_customColor);

    assertTextColorIsNot(R.id.customTextView, R.styleable.SampleCustomView, R.style.SampleCustomStyle_ColorState_Secondary, R.styleable.SampleCustomView_otherColor);
  }

  @Test(expected = BaristaException.class)
  public void checkColorStyleable_colorStateList_fail() {
    assertTextColorIs(R.id.customTextView, R.styleable.SampleCustomView, R.style.SampleCustomStyle_ColorState_Secondary, R.styleable.SampleCustomView_customColor);
  }

  @Test(expected = BaristaException.class)
  public void checkNotColorStyleable_colorStateList_fail() {
    assertTextColorIsNot(R.id.customTextView, R.styleable.SampleCustomView, R.style.SampleCustomStyle_ColorState, R.styleable.SampleCustomView_customColor);
  }
}