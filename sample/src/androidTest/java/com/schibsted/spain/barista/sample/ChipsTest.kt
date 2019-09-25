package com.schibsted.spain.barista.sample

import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertChecked
import com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertUnchecked
import com.schibsted.spain.barista.sample.util.SpyFailureHandlerRule
import org.junit.Rule
import org.junit.Test

class ChipsTest {

  @get:Rule
  var activityRule = ActivityTestRule(ChipsActivity::class.java)

  @get:Rule
  var spyFailureHandlerRule = SpyFailureHandlerRule()

  @Test
  fun assertCheckedChip() {
    assertChecked(R.id.checkedChip)

    spyFailureHandlerRule.assertNoEspressoFailures()
  }

  @Test
  fun assertUncheckedChip() {
    assertUnchecked(R.id.uncheckedChip)

    spyFailureHandlerRule.assertNoEspressoFailures()
  }
}