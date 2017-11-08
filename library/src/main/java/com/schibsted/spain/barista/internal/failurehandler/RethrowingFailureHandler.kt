package com.schibsted.spain.barista.internal.failurehandler

import android.support.test.espresso.FailureHandler
import android.view.View
import org.hamcrest.Matcher

class RethrowingFailureHandler : FailureHandler {
    override fun handle(error: Throwable, viewMatcher: Matcher<View>) {
        throw error
    }
}