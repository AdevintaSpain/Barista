package com.adevinta.android.barista.internal.failurehandler

import android.view.View
import androidx.test.espresso.FailureHandler
import org.hamcrest.Matcher

class RethrowingFailureHandler : FailureHandler {
  override fun handle(error: Throwable, viewMatcher: Matcher<View>) {
    throw error
  }
}