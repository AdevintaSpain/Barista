package com.adevinta.android.barista.sample

import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.interaction.BaristaEditTextInteractions.writeTo
import com.adevinta.android.barista.internal.failurehandler.BaristaException
import com.adevinta.android.barista.rule.flaky.Repeat
import org.junit.Rule
import org.junit.Test

class TextInputLayoutWithTextTest {

  @get:Rule
  var activityRule = ActivityTestRule(HintAndErrorActivity::class.java)

  @Test
  fun assertText() {
    writeTo(R.id.hintanderror_inputlayout, "Test text")
    assertDisplayed("Test text")
  }

  @Test
  fun assertNoText() {
    writeTo(R.id.hintanderror_inputlayout_editText, "Test text")
    assertNotDisplayed(R.id.hintanderror_inputlayout, "Other test text")
  }

  @Test(expected = BaristaException::class)
  fun assertTextFail() {
    writeTo(R.id.hintanderror_inputlayout_editText, "Test text")
    assertDisplayed("Other test text")
  }

  @Test(expected = BaristaException::class)
  fun assertNoTextFail() {
    writeTo(R.id.hintanderror_inputlayout_editText, "Test text")
    assertNotDisplayed("Test text")
  }
}