package com.schibsted.spain.barista.sample.util

import android.support.test.espresso.Espresso
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class FailureHandlerValidatorRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        val testAnnotation = description.getAnnotation(Test::class.java)
        val hasExpectedError = Test.None::class.java != testAnnotation.expected.java
        val errorsAllowed = if (hasExpectedError) 1 else 0
        val failureHandler = SpyFailureHandler()

        return object : Statement() {
            override fun evaluate() {
                Espresso.setFailureHandler(failureHandler)

                base.evaluate()

                checkFailureHandler(errorsAllowed, failureHandler)
            }
        }
    }

    private fun checkFailureHandler(errorsAllowed: Int, failureHandler: SpyFailureHandler) {
        val espressoErrorsCount = failureHandler.capturedFailures.size
        val exceptions = failureHandler.capturedFailures.map { it.throwable }
        if (espressoErrorsCount > errorsAllowed) {
            throw AssertionError("Expected at most $errorsAllowed exception, but $espressoErrorsCount were sent to Espresso: $exceptions")
        }
    }
}