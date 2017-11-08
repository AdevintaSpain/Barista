package com.schibsted.spain.barista.internal.failurehandler

import android.support.test.espresso.FailureHandler
import android.support.test.espresso.getFailureHandler
import android.view.View
import org.hamcrest.Matcher

class SpyFailureHandler : FailureHandler {

    val capturedFailures: MutableList<EspressoFailure> = ArrayList()

    override fun handle(error: Throwable, viewMatcher: Matcher<View>) {
        capturedFailures.add(EspressoFailure(error, viewMatcher))
        throw error
    }

    fun firstError() = capturedFailures.firstOrNull()
    fun lastError() = capturedFailures.lastOrNull()


    fun resendFirstError(customMessage: String) {
        firstError()?.let { (throwable, matcher) ->
            val baristaException = BaristaException(customMessage, throwable)
            getFailureHandler().handle(baristaException, matcher)
        }
    }

    fun resendLastError(customMessage: String) {
        lastError()?.let { (throwable, matcher) ->
            val baristaException = BaristaException(customMessage, throwable)
            getFailureHandler().handle(baristaException, matcher)
        }
    }
}
