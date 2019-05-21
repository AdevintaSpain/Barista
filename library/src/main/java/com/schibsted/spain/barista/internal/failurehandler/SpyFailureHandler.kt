package com.schibsted.spain.barista.internal.failurehandler

import androidx.test.espresso.FailureHandler
import androidx.test.espresso.getFailureHandler
import android.view.View
import org.hamcrest.Matcher

open class SpyFailureHandler : FailureHandler {

  val capturedFailures = mutableListOf<EspressoFailure>()

  open val capturedFailuresCount: Int
    get() = capturedFailures.size

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
