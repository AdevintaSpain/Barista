package com.schibsted.spain.barista.sample.util

import android.support.test.espresso.Espresso
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import org.assertj.core.api.Assertions.assertThat
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class SpyFailureHandlerRule : TestRule {

  private val failureHandler = SpyFailureHandler()

  override fun apply(base: Statement, description: Description): Statement {

    return object : Statement() {
      override fun evaluate() {
        Espresso.setFailureHandler(failureHandler)

        base.evaluate()
      }
    }
  }

  fun assertNoEspressoFailures() {
    assertEspressoFailures(0)
  }

  fun assertEspressoFailures(count: Int) {
    assertThat(failureHandler.capturedFailures).hasSize(count)
  }
}