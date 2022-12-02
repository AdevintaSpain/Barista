package com.adevinta.android.barista.internal.matcher

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf

fun withCompatText(string: String): Matcher<View> {
  return anyOf(
    withText(string),
    allOf(
      withParent(isAssignableFrom(TextInputLayout::class.java)),
      hasDescendant(withText(string))
    )
  )
}

fun withCompatText(stringMatcher: Matcher<String>): Matcher<View> {
  return anyOf(
    withText(stringMatcher),
    allOf(
      withParent(isAssignableFrom(TextInputLayout::class.java)),
      hasDescendant(withText(stringMatcher))
    )
  )
}