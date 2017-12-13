package com.schibsted.spain.barista.sample;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertTextColorIs;

@RunWith(AndroidJUnit4.class)
public class ColorsTest {

  @Rule
  public ActivityTestRule<ColorsActivity> activityRule = new ActivityTestRule<>(ColorsActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkSimpleColor() {
    assertTextColorIs(R.id.textRed, R.color.red);
  }

  @Test
  public void checkColorList_whenDefault() {
    assertTextColorIs(R.id.textSelectorDefault, R.color.selector_default_disabled_checked);
    assertTextColorIs(R.id.textSelectorDefault, R.color.defaultColor);
  }

  @Test
  public void checkColorList_whenDisabled() {
    assertTextColorIs(R.id.textSelectorDisabled, R.color.selector_default_disabled_checked);
    assertTextColorIs(R.id.textSelectorDisabled, R.color.disabled);
  }

  @Test
  public void checkColorList_whenChecked() {
    assertTextColorIs(R.id.textSelectorChecked, R.color.selector_default_disabled_checked);
    assertTextColorIs(R.id.textSelectorChecked, R.color.checked);
  }

}