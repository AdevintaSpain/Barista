package com.schibsted.spain.barista.internal.viewaction

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.view.View
import android.widget.SeekBar
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

object SeekBarActions {
  const val DESCRIPTION = "Set SeekBar progress."

  fun setProgress(progress: Int): ViewAction {
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

  fun setProgressToMin(): ViewAction = setProgress(0)

  fun setProgressToMax(): ViewAction {
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
