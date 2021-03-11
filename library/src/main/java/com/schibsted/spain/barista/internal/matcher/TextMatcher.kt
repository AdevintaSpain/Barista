package com.schibsted.spain.barista.internal.matcher

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.Is

fun withCompatText(string: String): Matcher<View> {
  return withCompatText(Is.`is`<String>(string))
}

fun withCompatText(stringMatcher: Matcher<String>): Matcher<View> {
  return withCompatTextMatcher(stringMatcher)
}

internal fun withCompatTextMatcher(stringMatcher: Matcher<String>): BoundedMatcher<View, View> {
  return object : BoundedMatcher<View, View>(View::class.java) {

    override fun matchesSafely(item: View): Boolean {

      val textView: TextView = when (item) {
        is TextInputLayout -> item.editText
        is TextView -> item
        else -> null
      } ?: return false

      if (stringMatcher.matches(textView.text.toString())) {
        return true
      } else if (textView.transformationMethod != null) {
        val transformedText: CharSequence = textView.transformationMethod.getTransformation(textView.text, textView)
        return stringMatcher.matches(transformedText.toString())
      }

      return false
    }

    override fun describeTo(description: Description) {
      description.appendText("with text on TextInputLayout: ")
      stringMatcher.describeTo(description)
    }
  }
}