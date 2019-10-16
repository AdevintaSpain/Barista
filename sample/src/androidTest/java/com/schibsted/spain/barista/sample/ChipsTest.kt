package com.schibsted.spain.barista.sample

import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertChecked
import com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertUnchecked
import com.schibsted.spain.barista.assertion.BaristaChipAssertions.assertAnyChipSelected
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaChipInteractions.closeChip
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.internal.failurehandler.BaristaException
import com.schibsted.spain.barista.sample.util.SpyFailureHandlerRule
import junit.framework.AssertionFailedError
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.ThrowableAssert.catchThrowable
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

  @Test
  fun assertUncheckedChip_failsWhenNeeded() {
    val thrown = catchThrowable{assertUnchecked(R.id.checkedChip)}

    spyFailureHandlerRule.assertEspressoFailures(1)

    assertThat(thrown).isInstanceOf(BaristaException::class.java)
            .hasMessage("View (with id: com.schibsted.spain.barista.sample:id/checkedChip) didn't match condition (not with checkbox state: is <true>)")
            .hasCauseInstanceOf(AssertionFailedError::class.java)
  }

  @Test
  fun checkOnChipOnSingleChoiceChipGroup_hasOnlyOneSelectedChip() {
    clickOn(R.id.chip1)

    assertAnyChipSelected(R.id.singleSelectionChildGroup)

    spyFailureHandlerRule.assertNoEspressoFailures()
  }

  @Test
  fun checkOnChipOnSingleChoiceChipGroup_hasOnlyOneSelectedChip_failsWhenNeeded() {
    val thrown = catchThrowable{assertAnyChipSelected(R.id.singleSelectionChildGroup)}

    spyFailureHandlerRule.assertEspressoFailures(1)

    assertThat(thrown).isInstanceOf(BaristaException::class.java)
            .hasMessage("View (with id: com.schibsted.spain.barista.sample:id/singleSelectionChildGroup) didn't match condition (custom condition [use `assertionDescription` parameter on `assertAny` to setup descriptive message])")
            .hasCauseInstanceOf(AssertionFailedError::class.java)
  }

  @Test
  fun assertCloseChip() {
    closeChip(R.id.closeChip)

    assertDisplayed(R.id.closeChipText)

    spyFailureHandlerRule.assertNoEspressoFailures()
  }
}