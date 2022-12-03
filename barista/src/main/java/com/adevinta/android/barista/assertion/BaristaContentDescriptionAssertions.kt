package com.adevinta.android.barista.assertion

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.adevinta.android.barista.internal.assertAny
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object BaristaContentDescriptionAssertions {

  @JvmStatic
  fun assertHasContentDescription(@IdRes viewId: Int) {
    ViewMatchers.withId(viewId).assertAny(matchHasContentDescription())
  }

  @JvmStatic
  fun assertContentDescription(@IdRes viewId: Int, @StringRes text: Int) {
    val resourceString = InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(text)
    assertContentDescription(viewId, resourceString)
  }

  @JvmStatic
  fun assertContentDescription(@IdRes viewId: Int, text: String) {
    ViewMatchers.withId(viewId).assertAny(matchContentDescription(text))
  }

  private fun matchHasContentDescription(): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("with content description")
      }

      override fun matchesSafely(item: View): Boolean {
        return item.contentDescription.isNotBlank()
      }
    }
  }

  private fun matchContentDescription(expectedContentDescription: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("with content description: ").appendText(expectedContentDescription)
      }

      override fun matchesSafely(item: View): Boolean {
        return item.contentDescription == expectedContentDescription
      }
    }
  }
}