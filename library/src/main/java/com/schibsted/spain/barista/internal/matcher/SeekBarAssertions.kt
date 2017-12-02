package com.schibsted.spain.barista.internal.matcher

import android.support.test.espresso.AmbiguousViewMatcherException
import android.support.test.espresso.Espresso
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import android.widget.SeekBar
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.failurehandler.description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
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

  private fun assertDisplayed(matcher: Matcher<View>) {
    val spyFailureHandler = SpyFailureHandler()
    try {
      Espresso.onView(matcher)
          .withFailureHandler(spyFailureHandler)
          .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    } catch (multipleViews: AmbiguousViewMatcherException) {
      try {
        Espresso.onView(HelperMatchers.firstViewOf(Matchers.allOf(matcher, ViewMatchers.isDisplayed())))
            .withFailureHandler(spyFailureHandler)
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
      } catch (notDisplayedError: NoMatchingViewException) {
        spyFailureHandler.resendFirstError("View ${matcher.description()} wasn't displayed on the screen")
      }
    }
  }
}
