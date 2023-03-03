package com.adevinta.android.barista.sample

import android.graphics.Color
import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertTextColorIs
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertTextColorIsNot
import com.adevinta.android.barista.internal.failurehandler.BaristaException
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule
import org.junit.Rule
import org.junit.Test

class ColorsTest {
  @Rule
  var activityRule = ActivityTestRule(ColorsActivity::class.java)

  @Rule
  var handlerValidator = FailureHandlerValidatorRule()

  @Test
  fun checkSimpleColor() {
    assertTextColorIs(R.id.textRed, R.color.red)
  }

  @Test
  fun checkColorList_whenDefault() {
    assertTextColorIs(R.id.textSelectorDefault, R.color.selector_default_disabled_checked)
    assertTextColorIs(R.id.textSelectorDefault, R.color.defaultColor)
  }

  @Test
  fun checkColorAttribute() {
    assertTextColorIs(R.id.textColorAttribute, R.attr.colorPrimary)
    assertTextColorIsNot(R.id.textColorAttribute, R.attr.colorError)
  }

  @Test
  fun checkColorInt() {
    assertTextColorIs(R.id.textColorInt, Color.parseColor("#ff0000"))
    assertTextColorIsNot(R.id.textColorInt, Color.parseColor("#ff00ff"))
  }

  @Test
  fun checkColorStyleable() {
    assertTextColorIs(
      R.id.customTextView,
      R.styleable.SampleCustomView,
      R.style.SampleCustomStyle,
      R.styleable.SampleCustomView_customColor
    )
    assertTextColorIsNot(
      R.id.customTextView,
      R.styleable.SampleCustomView,
      R.style.SampleCustomStyle_Green,
      R.styleable.SampleCustomView_customColor
    )
    assertTextColorIsNot(
      R.id.customTextView,
      R.styleable.SampleCustomView,
      R.style.SampleCustomStyle,
      R.styleable.SampleCustomView_otherColor
    )
  }

  @Test
  fun checkColorList_whenDisabled() {
    assertTextColorIs(R.id.textSelectorDisabled, R.color.selector_default_disabled_checked)
    assertTextColorIs(R.id.textSelectorDisabled, R.color.disabled)
  }

  @Test
  fun checkColorList_whenChecked() {
    assertTextColorIs(R.id.textSelectorChecked, R.color.selector_default_disabled_checked)
    assertTextColorIs(R.id.textSelectorChecked, R.color.checked)
  }

  @Test(expected = BaristaException::class)
  fun checkSimpleColor_fails() {
    assertTextColorIs(R.id.textRed, R.color.blue)
  }

  @Test(expected = BaristaException::class)
  fun checkColorAttr_fails() {
    assertTextColorIs(R.id.textColorAttribute, R.attr.colorError)
  }

  @Test(expected = BaristaException::class)
  fun checkColorInt_fails() {
    assertTextColorIs(R.id.textColorInt, Color.parseColor("#00ff00"))
  }

  @Test(expected = BaristaException::class)
  fun checkNotSimpleColor_fails() {
    assertTextColorIsNot(R.id.textRed, R.color.red)
  }

  @Test(expected = BaristaException::class)
  fun checkNotColorAttr_fails() {
    assertTextColorIsNot(R.id.textColorAttribute, R.attr.colorPrimary)
  }

  @Test(expected = BaristaException::class)
  fun checkNotColorInt_fails() {
    assertTextColorIsNot(R.id.textColorInt, Color.parseColor("#ff0000"))
  }

  @Test(expected = BaristaException::class)
  fun checkColorList_whenDefault_fails() {
    assertTextColorIs(R.id.textSelectorDefault, R.color.checked)
  }

  @Test(expected = BaristaException::class)
  fun checkColorList_whenDisabled_fails() {
    assertTextColorIs(R.id.textSelectorDisabled, R.color.checked)
  }

  @Test(expected = BaristaException::class)
  fun checkColorList_whenChecked_fails() {
    assertTextColorIs(R.id.textSelectorChecked, R.color.disabled)
  }

  @Test(expected = BaristaException::class)
  fun checkColorStyleable_fail() {
    assertTextColorIs(
      R.id.customTextView,
      R.styleable.SampleCustomView,
      R.style.SampleCustomStyle_Green,
      R.styleable.SampleCustomView_customColor
    )
  }

  @Test(expected = BaristaException::class)
  fun checkNotColorStyleable_fail() {
    assertTextColorIsNot(
      R.id.customTextView,
      R.styleable.SampleCustomView,
      R.style.SampleCustomStyle,
      R.styleable.SampleCustomView_customColor
    )
  }
}