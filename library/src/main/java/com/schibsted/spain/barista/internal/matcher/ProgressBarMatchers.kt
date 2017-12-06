package com.schibsted.spain.barista.internal.matcher

import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.widget.ProgressBar
import android.widget.SeekBar
import org.hamcrest.Description

object ProgressBarMatchers {

  class SeekBarProgressMatcher(private val expectedProgress: Int) : BoundedMatcher<View, ProgressBar>(
      ProgressBar::class.java) {

    override fun matchesSafely(progressBar: ProgressBar) = progressBar.progress == expectedProgress

    override fun describeTo(description: Description) {
      description.appendText("should be equal to progress $expectedProgress")
    }
  }

  @JvmStatic
  fun assertProgress(expectedProgress: Int) = SeekBarProgressMatcher(expectedProgress)

  @JvmStatic
  fun assertProgressIsMin() = SeekBarProgressMatcher(0)

  @JvmStatic
  fun assertProgressIsMax(): BoundedMatcher<View, SeekBar> {
    return object : BoundedMatcher<View, SeekBar>(SeekBar::class.java) {

      override fun matchesSafely(seekBar: SeekBar) = seekBar.progress == seekBar.max

      override fun describeTo(description: Description) {
        description.appendText("should be equal to max progress")
      }
    }
  }
}
