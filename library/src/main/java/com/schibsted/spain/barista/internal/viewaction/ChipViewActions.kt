package com.schibsted.spain.barista.internal.viewaction

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.google.android.material.chip.Chip
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

object ChipViewActions {

  @JvmStatic
  fun removeChip(): ViewAction {
    return object : ViewAction {
      override fun getConstraints(): Matcher<View> {
        return allOf(isDisplayed(), isAssignableFrom(Chip::class.java))
      }

      override fun perform(uiController: UiController, view: View) {
        val chip = view as Chip
        chip.performCloseIconClick()
      }

      override fun getDescription(): String = "Close chip"
    }
  }
}