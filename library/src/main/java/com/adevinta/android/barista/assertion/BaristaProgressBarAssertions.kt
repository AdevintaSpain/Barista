package com.adevinta.android.barista.assertion

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.adevinta.android.barista.internal.matcher.DisplayedMatchers.displayedWithId
import com.adevinta.android.barista.internal.matcher.ProgressBarMatchers.withMaxProgress
import com.adevinta.android.barista.internal.matcher.ProgressBarMatchers.withMinProgress
import com.adevinta.android.barista.internal.matcher.ProgressBarMatchers.withProgress

object BaristaProgressBarAssertions {

  @JvmStatic
  fun assertProgress(@IdRes progressBarId: Int, expectedProgress: Int) {
    onView(displayedWithId(progressBarId)).check(matches(withProgress(expectedProgress)))
  }

  @JvmStatic
  fun assertProgressIsMin(@IdRes progressBarId: Int) {
    onView(displayedWithId(progressBarId)).check(matches(withMinProgress()))
  }

  @JvmStatic
  fun assertProgressIsMax(@IdRes progressBarId: Int) {
    onView(displayedWithId(progressBarId)).check(matches(withMaxProgress()))
  }
}
