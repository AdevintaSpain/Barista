package com.adevinta.android.barista.internal.viewaction

import android.view.View
import android.widget.Checkable
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.BaseMatcher
import org.hamcrest.CoreMatchers.isA
import org.hamcrest.Description
import org.hamcrest.Matcher

object CheckableViewActions {
  const val DESCRIPTION = "Click on a checkable view like checkboxes, radiobuttons, etc..."

  @JvmStatic
  fun setCheckedState(checked: Boolean): ViewAction {
    return object : ViewAction {
      override fun getConstraints(): Matcher<View> {
        return object : BaseMatcher<View>() {
          override fun matches(item: Any?) = isA(Checkable::class.java).matches(item)
          override fun describeMismatch(item: Any?, mismatchDescription: Description?) {}
          override fun describeTo(description: Description?) {}
        }
      }

      override fun getDescription() = DESCRIPTION

      override fun perform(uiController: UiController?, view: View) {
        (view as? Checkable)?.isChecked = checked
      }
    }
  }
}