package com.adevinta.android.barista.assertion

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.internal.assertAny
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object BaristaHintAssertions {

  @JvmStatic
  fun assertHint(@IdRes viewId: Int, @StringRes text: Int) {
    val resourceString = ApplicationProvider.getApplicationContext<Context>().resources.getString(text)
    assertHint(viewId, resourceString)
  }

  @JvmStatic
  fun assertHint(@IdRes viewId: Int, text: String) {
    withId(viewId).assertAny(matchHint(text))
  }

  private fun matchHint(expectedHint: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("with hint: ").appendText(expectedHint)
      }

      override fun matchesSafely(item: View): Boolean {
        return item.tryGetHintText() == expectedHint
      }

      override fun describeMismatchSafely(item: View, mismatchDescription: Description) {
        mismatchDescription.appendText("with hint: ").appendValue(item.tryGetHintText())
      }

      private fun View.tryGetHintText(): String? {
        return when (this) {
          is TextInputLayout -> {
            hint?.toString()
          }
          is TextInputEditText -> {
            val hint = ((this.parent as FrameLayout).parent as TextInputLayout).hint
            hint?.toString()
          }
          is EditText -> {
            hint?.toString()
          }
          else -> {
            throw UnsupportedOperationException("View of class ${javaClass.simpleName} not supported")
          }
        }
      }
    }
  }
}