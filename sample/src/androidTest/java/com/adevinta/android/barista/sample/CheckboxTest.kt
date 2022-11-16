package com.adevinta.android.barista.sample

import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaCheckedAssertions.assertChecked
import com.adevinta.android.barista.assertion.BaristaCheckedAssertions.assertUnchecked
import com.adevinta.android.barista.interaction.BaristaCheckboxInteractions.check
import com.adevinta.android.barista.interaction.BaristaCheckboxInteractions.uncheck
import com.adevinta.android.barista.sample.util.SpyFailureHandlerRule
import org.junit.Rule
import org.junit.Test

class CheckboxTest {

  @get:Rule
  var activityRule = ActivityTestRule(CheckBoxActivity::class.java)

  @get:Rule
  var spyFailureHandlerRule = SpyFailureHandlerRule()

  @Test
  fun check_checkBox() {
    check(R.id.first_item)

    assertChecked(R.id.first_item)
    assertUnchecked(R.id.second_item)
    spyFailureHandlerRule.assertNoEspressoFailures()
  }

  @Test
  fun uncheck_checkBox() {
    check(R.id.first_item)
    check(R.id.second_item)

    uncheck(R.id.first_item)

    assertUnchecked(R.id.first_item)
    assertChecked(R.id.second_item)
    spyFailureHandlerRule.assertNoEspressoFailures()
  }
}