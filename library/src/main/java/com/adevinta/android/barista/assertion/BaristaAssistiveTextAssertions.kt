package com.adevinta.android.barista.assertion

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.adevinta.android.barista.internal.assertAny
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object BaristaAssistiveTextAssertions {
  @JvmStatic
  fun assertAssistiveText(@IdRes viewId: Int, @StringRes text: Int) {
    val resourceString = InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(text)
      assertAssistiveText(viewId, resourceString)
  }

  @JvmStatic
  fun assertAssistiveText(@IdRes viewId: Int, text: String) {
    ViewMatchers.withId(viewId).assertAny(matchAssistiveText(text))
  }

  private fun matchAssistiveText(expectedAssistiveText: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("with helper text: ").appendValue(expectedAssistiveText)
      }

      override fun matchesSafely(item: View): Boolean {
        return item.tryGetHelperText() == expectedAssistiveText
      }

      override fun describeMismatchSafely(item: View, mismatchDescription: Description) {
        mismatchDescription.appendText("with helper text: ").appendValue(item.tryGetHelperText())
      }

      private fun View.tryGetHelperText(): String? {
        return when (this) {
          is TextInputLayout -> helperText?.toString()
          else -> {
            throw UnsupportedOperationException("View of class ${javaClass.simpleName} not supported")
          }
        }
      }
    }
  }
}