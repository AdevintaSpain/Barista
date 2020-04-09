package com.schibsted.spain.barista.sample;

import android.graphics.Color;
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
  public void checkColorAttribute() {
    assertTextColorIs(R.id.textColorAttribute, R.attr.colorPrimary);
    assertTextColorIsNot(R.id.textColorAttribute, R.attr.colorError);
  }

  @Test
  public void checkColorInt() {
    assertTextColorIs(R.id.textColorInt, Color.parseColor("#ff0000"));
    assertTextColorIsNot(R.id.textColorInt,  Color.parseColor("#ff00ff"));
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

  @Test(expected = BaristaException.class)
  public void checkSimpleColor_fails() {
    assertTextColorIs(R.id.textRed, R.color.blue);
  }

  @Test(expected = BaristaException.class)
  public void checkColorAttr_fails() {
    assertTextColorIs(R.id.textColorAttribute, R.attr.colorError);
  }

  @Test(expected = BaristaException.class)
  public void checkColorInt_fails() {
    assertTextColorIs(R.id.textColorInt, Color.parseColor("#00ff00"));
  }

  @Test(expected = BaristaException.class)
  public void checkNotSimpleColor_fails() {
    assertTextColorIsNot(R.id.textRed, R.color.red);
  }

  @Test(expected = BaristaException.class)
  public void checkNotColorAttr_fails() {
    assertTextColorIsNot(R.id.textColorAttribute, R.attr.colorPrimary);
  }

  @Test(expected = BaristaException.class)
  public void checkNotColorInt_fails() {
    assertTextColorIsNot(R.id.textColorInt, Color.parseColor("#ff0000"));
  }

  @Test(expected = BaristaException.class)
  public void checkColorList_whenDefault_fails() {
    assertTextColorIs(R.id.textSelectorDefault, R.color.checked);
  }

  @Test(expected = BaristaException.class)
  public void checkColorList_whenDisabled_fails() {
    assertTextColorIs(R.id.textSelectorDisabled, R.color.checked);
  }

  @Test(expected = BaristaException.class)
  public void checkColorList_whenChecked_fails() {
    assertTextColorIs(R.id.textSelectorChecked, R.color.disabled);
  }
}