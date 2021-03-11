package com.schibsted.spain.barista.sample

import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import com.schibsted.spain.barista.internal.failurehandler.BaristaException
import org.junit.Rule
import org.junit.Test

class TextInputLayoutWithTextTest {

  @get:Rule
  var activityRule = ActivityTestRule(HintAndErrorActivity::class.java)

  @Test
  fun assertText() {
    writeTo(R.id.hintanderror_inputlayout_editText, "Test text")
    assertDisplayed(R.id.hintanderror_inputlayout, "Test text")
  }

  @Test
  fun assertNoText() {
    writeTo(R.id.hintanderror_inputlayout_editText, "Test text")
    assertNotDisplayed(R.id.hintanderror_inputlayout, "Other test text")
  }

  @Test(expected = BaristaException::class)
  fun assertTextFail() {
    writeTo(R.id.hintanderror_inputlayout_editText, "Test text")
    assertDisplayed(R.id.hintanderror_inputlayout, "Other test text")
  }

  @Test(expected = BaristaException::class)
  fun assertNoTextFail() {
    writeTo(R.id.hintanderror_inputlayout_editText, "Test text")
    assertNotDisplayed(R.id.hintanderror_inputlayout, "Test text")
  }
}