package com.schibsted.spain.barista.assertion

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers
import com.google.android.material.textfield.TextInputLayout
import com.schibsted.spain.barista.internal.assertAny
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object BaristaErrorAssertions {

  @JvmStatic
  fun assertError(@IdRes viewId: Int, @StringRes text: Int) {
    val resourceString = ApplicationProvider.getApplicationContext<Context>().resources.getString(text)
    assertError(viewId, resourceString)
  }

  @JvmStatic
  fun assertError(@IdRes viewId: Int, text: String) {
    ViewMatchers.withId(viewId).assertAny(matchError(text))
  }

  @JvmStatic
  fun assertNoError(@IdRes viewId: Int) {
    ViewMatchers.withId(viewId).assertAny(matchNoError())
  }

  private fun matchError(expectedError: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("with error: ").appendText(expectedError)
      }

      override fun matchesSafely(item: View): Boolean {
        return when (item) {
          is TextView -> expectedError == item.error.toString()
          is TextInputLayout -> expectedError == item.error.toString()
          else -> {
            throw UnsupportedOperationException("View of class ${item.javaClass.simpleName} not supported")
          }
        }
      }
    }
  }

  private fun matchNoError(): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("without error")
      }

      override fun matchesSafely(item: View): Boolean {
        return when (item) {
          is TextView -> item.error.isNullOrEmpty()
          is TextInputLayout -> item.error.isNullOrEmpty()
          else -> {
            throw UnsupportedOperationException("View of class ${item.javaClass.simpleName} not supported")
          }
        }
      }
    }
  }
}