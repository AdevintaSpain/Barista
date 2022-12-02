package com.adevinta.android.barista.internal.failurehandler

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.FailureHandler
import androidx.test.espresso.getFailureHandler
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
