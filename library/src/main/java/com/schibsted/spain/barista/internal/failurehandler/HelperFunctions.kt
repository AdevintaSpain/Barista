package com.schibsted.spain.barista.internal.failurehandler

import android.support.test.espresso.Espresso
import android.support.test.espresso.FailureHandler
import android.support.test.espresso.getFailureHandler
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

fun withFailureHandler(temporaryHandler: FailureHandler, testCode: () -> Unit) {
    val globalHandler = getFailureHandler()
    Espresso.setFailureHandler(temporaryHandler)
    try {
        testCode()
    } finally {
        Espresso.setFailureHandler(globalHandler)
    }
}


fun Matcher<View>.description(): String {
    val matcherDescription = StringDescription()
    describeTo(matcherDescription)
    return matcherDescription.toString()
}
