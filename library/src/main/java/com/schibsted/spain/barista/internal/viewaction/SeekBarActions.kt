package com.schibsted.spain.barista.internal.viewaction

import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import android.view.View
import android.widget.SeekBar
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

object SeekBarActions {
  const val DESCRIPTION = "Set SeekBar progress."

  fun setSeekBarProgressTo(progress: Int): ViewAction {
    return object : ViewAction {
      override fun getConstraints(): Matcher<View> {
        return allOf(isDisplayed(), isAssignableFrom(SeekBar::class.java))
      }

      override fun getDescription(): String {
        return DESCRIPTION
      }

      override fun perform(uiController: UiController, view: View) {
        val seekBar = view as SeekBar
        seekBar.progress = progress
      }
    }
  }

  fun setSeekBarProgressToMin(): ViewAction = setSeekBarProgressTo(0)

  fun setSeekBarProgressToMax(): ViewAction {
    return object : ViewAction {
      override fun getConstraints(): Matcher<View> {
        return allOf(isDisplayed(), isAssignableFrom(SeekBar::class.java))
      }

      override fun getDescription(): String {
        return DESCRIPTION
      }

      override fun perform(uiController: UiController, view: View) {
        val seekBar = view as SeekBar
        seekBar.progress = seekBar.max
      }
    }
  }
}
