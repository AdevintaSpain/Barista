package com.schibsted.spain.barista.internal.failurehandler

import androidx.test.espresso.Espresso
import androidx.test.espresso.FailureHandler
import androidx.test.espresso.getFailureHandler
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
