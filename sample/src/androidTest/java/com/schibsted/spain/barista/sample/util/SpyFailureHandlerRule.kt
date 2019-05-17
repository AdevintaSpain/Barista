package com.schibsted.spain.barista.sample.util

import androidx.test.espresso.Espresso
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import org.assertj.core.api.Assertions.assertThat
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class SpyFailureHandlerRule(private val failureHandler: SpyFailureHandler = SpyFailureHandler()) : TestRule {

  private var asserted = false

  override fun apply(base: Statement, description: Description): Statement {

    return object : Statement() {
      override fun evaluate() {
        Espresso.setFailureHandler(failureHandler)
        base.evaluate()
        if (!asserted) {
          throw AssertionError("Forgot to assert the test method with SpyFailureHandlerRule")
        }
      }
    }
  }

  fun assertNoEspressoFailures() {
    assertEspressoFailures(0)
    asserted = true
  }

  fun assertEspressoFailures(expectedFailures: Int) {
    val receivedFailures = failureHandler.capturedFailuresCount
    assertThat(receivedFailures)
        .`as`("Expected %d EspressoFailure but received %d", expectedFailures, receivedFailures)
        .isEqualTo(expectedFailures)
    asserted = true
  }
}