package com.schibsted.spain.barista.sample.util

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import org.junit.Test
import org.junit.runner.Description
import org.junit.runners.model.Statement

class SpyFailureHandlerRuleTest {

  val testMethod = mock<Statement>()
  val description = mock<Description>()
  val failureHandler = mock<SpyFailureHandler>()

  val spyFailureHandlerRule = SpyFailureHandlerRule(failureHandler)

  @Test(expected = AssertionError::class)
  fun should_fail_when_nothing_asserted() {
    // asserted = no
    // failed = no
    spyFailureHandlerRule.apply(testMethod, description).evaluate()
  }

  @Test(expected = RuntimeException::class)
  fun should_rethrow_when_nothing_asserted_and_test_failed() {
    // asserted = no
    // failed = yes
    given(testMethod.evaluate()).will {
      throw RuntimeException("mocked test failed!")
    }

    spyFailureHandlerRule.apply(testMethod, description).evaluate()
  }

  @Test
  fun should_not_fail_when_asserted_no_failures() {
    // asserted = yes
    // failed = no
    given(testMethod.evaluate()).will {
      spyFailureHandlerRule.assertNoEspressoFailures()
    }

    spyFailureHandlerRule.apply(testMethod, description).evaluate()
  }

  @Test(expected = RuntimeException::class)
  fun should_rethrow_when_asserted_failure_and_test_failed() {
    // asserted = yes
    // failed = yes
    given(failureHandler.capturedFailuresCount).willReturn(1)
    given(testMethod.evaluate()).will {
      spyFailureHandlerRule.assertEspressoFailures(1)
      throw RuntimeException("mocked test failed!")
    }

    spyFailureHandlerRule.apply(testMethod, description).evaluate()
  }
}