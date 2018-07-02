package com.schibsted.spain.barista.sample.util

import android.support.test.espresso.Espresso
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import org.assertj.core.api.Assertions.assertThat
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class SpyFailureHandlerRule : TestRule {

  private val failureHandler = SpyFailureHandler()
  private var failed = false
  private var asserted = false

  override fun apply(base: Statement, description: Description): Statement {

    return object : Statement() {
      override fun evaluate() {
        Espresso.setFailureHandler(failureHandler)
        try {
          base.evaluate()
        } catch (t: Throwable) {
          failed = true
          throw t
        } finally {
          if (!failed && !asserted) {
            throw AssertionError("Forgot to assert the test method with SpyFailureHandlerRule")
          }
        }
      }
    }
  }

  fun assertNoEspressoFailures() {
    assertEspressoFailures(0)
    asserted = true
  }

  fun assertEspressoFailures(expectedFailures: Int) {
    val receivedFailures = failureHandler.capturedFailures.size
    assertThat(receivedFailures)
        .`as`("Expected %d EspressoFailure but received %d", expectedFailures, receivedFailures)
        .isEqualTo(expectedFailures)
    asserted = true
  }
}