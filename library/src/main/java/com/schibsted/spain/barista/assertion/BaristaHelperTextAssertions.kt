package com.schibsted.spain.barista.assertion

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.matcher.ViewMatchers
import com.google.android.material.textfield.TextInputLayout
import com.schibsted.spain.barista.internal.assertAny
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object BaristaHelperTextAssertions {
  @JvmStatic
  fun assertHelperText(@IdRes viewId: Int, @StringRes text: Int) {
    val resourceString = InstrumentationRegistry.getTargetContext().resources.getString(text)
    assertHelperText(viewId, resourceString)
  }

  @JvmStatic
  fun assertHelperText(@IdRes viewId: Int, text: String) {
    ViewMatchers.withId(viewId).assertAny(matchError(text))
  }

  private fun matchError(expectedHelperText: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("with helper text: ").appendText(expectedHelperText)
      }

      override fun matchesSafely(item: View): Boolean {
        return when (item) {
          is TextInputLayout -> expectedHelperText == item.helperText.toString()
          else -> {
            throw UnsupportedOperationException("View of class ${item.javaClass.simpleName} not supported")
          }
        }
      }
    }
  }
}