package com.schibsted.spain.barista.internal.matcher

import androidx.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.widget.ProgressBar
import org.hamcrest.Description

object ProgressBarMatchers {

  class ProgressBarMatcher(private val expectedProgress: Int) : BoundedMatcher<View, ProgressBar>(
      ProgressBar::class.java) {

    override fun matchesSafely(progressBar: ProgressBar) = progressBar.progress == expectedProgress

    override fun describeTo(description: Description) {
      description.appendText("should be equal to progress $expectedProgress")
    }
  }

  @JvmStatic
  fun withProgress(expectedProgress: Int) = ProgressBarMatcher(expectedProgress)

  @JvmStatic
  fun withMinProgress() = ProgressBarMatcher(0)

  @JvmStatic
  fun withMaxProgress(): BoundedMatcher<View, ProgressBar> {
    return object : BoundedMatcher<View, ProgressBar>(ProgressBar::class.java) {

      override fun matchesSafely(seekBar: ProgressBar) = seekBar.progress == seekBar.max

      override fun describeTo(description: Description) {
        description.appendText("should be equal to max progress")
      }
    }
  }
}
