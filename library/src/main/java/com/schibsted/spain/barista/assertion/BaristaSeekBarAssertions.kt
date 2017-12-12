package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedWithId
import com.schibsted.spain.barista.internal.matcher.ProgressBarMatchers.withMaxProgress
import com.schibsted.spain.barista.internal.matcher.ProgressBarMatchers.withMinProgress
import com.schibsted.spain.barista.internal.matcher.ProgressBarMatchers.withProgress

object BaristaSeekBarAssertions {

  @JvmStatic
  fun assertProgress(@IdRes seekBarId: Int, expectedProgress: Int) {
    onView(displayedWithId(seekBarId)).check(matches(withProgress(expectedProgress)))
  }

  @JvmStatic
  fun assertProgressIsMin(@IdRes seekBarId: Int) {
    onView(displayedWithId(seekBarId)).check(matches(withMinProgress()))
  }

  @JvmStatic
  fun assertProgressIsMax(@IdRes seekBarId: Int) {
    onView(displayedWithId(seekBarId)).check(matches(withMaxProgress()))
  }
}
