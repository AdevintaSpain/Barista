package com.adevinta.android.barista.assertion

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers
import com.adevinta.android.barista.internal.assertAny
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object BaristaErrorAssertions {

  @Deprecated(
    message = "Use assertErrorDisplayed(id, text)",
    replaceWith = ReplaceWith(
      "assertErrorDisplayed(viewId, text)",
      "com.adevinta.android.barista.assertion.BaristaErrorAssertions.assertErrorDisplayed"
    )
  )
  @JvmStatic
  fun assertError(@IdRes viewId: Int, @StringRes text: Int) {
    assertErrorDisplayed(viewId, text)
  }

  @Deprecated(
    message = "Use assertErrorDisplayed(id, text)",
    replaceWith = ReplaceWith(
      "assertErrorDisplayed(viewId, text)",
      "com.adevinta.android.barista.assertion.BaristaErrorAssertions.assertErrorDisplayed"
    )
  )
  @JvmStatic
  fun assertError(@IdRes viewId: Int, text: String) {
    assertErrorDisplayed(viewId, text)
  }

  @JvmStatic
  fun assertErrorDisplayed(@IdRes viewId: Int, @StringRes text: Int) {
    val resourceString = ApplicationProvider.getApplicationContext<Context>().resources.getString(text)
    assertErrorDisplayed(viewId, resourceString)
  }

  @JvmStatic
  fun assertErrorDisplayed(@IdRes viewId: Int, text: String) {
    ViewMatchers.withId(viewId).assertAny(matchError(text))
  }

  @JvmStatic
  fun assertNoErrorDisplayed(@IdRes viewId: Int) {
    ViewMatchers.withId(viewId).assertAny(matchNoError())
  }

  private fun matchError(expectedError: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("with error: ").appendValue(expectedError)
      }

      override fun describeMismatchSafely(item: View, mismatchDescription: Description) {
        mismatchDescription.appendText("with error: ").appendValue(item.tryGetErrorText())
      }

      override fun matchesSafely(item: View): Boolean {
        return expectedError == item.tryGetErrorText()
      }
    }
  }

  private fun matchNoError(): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("without error")
      }

      override fun describeMismatchSafely(item: View, mismatchDescription: Description) {
        mismatchDescription.appendText("with error: ").appendValue(item.tryGetErrorText())
      }

      override fun matchesSafely(item: View): Boolean {
        return item.tryGetErrorText().isNullOrEmpty()
      }
    }
  }

  private fun View.tryGetErrorText(): String? {
    return when (this) {
      is TextView -> error?.toString()
      is TextInputLayout -> error?.toString()
      else -> {
        throw UnsupportedOperationException("View of class ${javaClass.simpleName} not supported")
      }
    }
  }
}