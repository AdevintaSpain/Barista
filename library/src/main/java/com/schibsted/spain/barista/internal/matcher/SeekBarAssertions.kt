package com.schibsted.spain.barista.internal.matcher

import android.support.test.espresso.ViewAssertion
import android.widget.SeekBar
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`

object SeekBarAssertions {
  @JvmStatic
  fun assertProgress(expectedProgress: Int): ViewAssertion {
    return ViewAssertion { view, noViewFoundException ->
      if (noViewFoundException != null) {
        throw noViewFoundException
      }
      val seekBar = view as SeekBar
      assertThat(seekBar.progress, `is`(expectedProgress))
    }
  }

  @JvmStatic
  fun assertProgressIsMin(): ViewAssertion = assertProgress(0)

  @JvmStatic
  fun assertProgressIsMax(): ViewAssertion {
    return ViewAssertion { view, noViewFoundException ->
      if (noViewFoundException != null) {
        throw noViewFoundException
      }
      val seekBar = view as SeekBar
      assertThat(seekBar.progress, `is`(seekBar.max))
    }
  }
}
