package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
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
      override fun describeTo(description: Description?) {
        description?.let {
          description.appendText(" $expectedHint")
        }
      }

      override fun matchesSafely(item: View): Boolean {
        if (item is TextInputLayout) {
          return expectedHint == item.hint.toString()
        } else if (item is TextInputEditText) {
          val hint = ((item.parent as FrameLayout).parent as TextInputLayout).hint
          return expectedHint == hint
        } else {
          return (item as TextView).hint == expectedHint
        }
      }
    }
  }
}