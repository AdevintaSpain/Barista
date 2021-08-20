package com.adevinta.android.barista.sample

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaAssistiveTextAssertions.assertAssistiveText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class HelperTextTest {

  @get:Rule
  var activityRule = ActivityTestRule(HintAndErrorActivity::class.java)

  @Test
  fun assertHelperTextByString() {
    assertAssistiveText(R.id.texthelper_inputlayout, "This is a sample helper text")
  }

  @Test
  fun assertHelperTextByResource() {
    assertAssistiveText(R.id.texthelper_inputlayout, R.string.text_helper_text)
  }
}