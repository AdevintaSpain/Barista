package com.schibsted.spain.barista.assertion

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.matcher.ViewMatchers.withId
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import com.schibsted.spain.barista.internal.assertAny
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object BaristaHintAssertions {

  @JvmStatic
  fun assertHint(@IdRes viewId: Int, @StringRes text: Int) {
    val resourceString = InstrumentationRegistry.getTargetContext().resources.getString(text)
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
        return when (item) {
          is TextInputLayout -> {
            expectedHint == item.hint.toString()
          }
          is TextInputEditText -> {
            val hint = ((item.parent as FrameLayout).parent as TextInputLayout).hint
            expectedHint == hint
          }
          is EditText -> {
            item.hint == expectedHint
          }
          else -> {
            throw UnsupportedOperationException("View of class ${item.javaClass.simpleName} not supported")
          }
        }
      }
    }
  }
}