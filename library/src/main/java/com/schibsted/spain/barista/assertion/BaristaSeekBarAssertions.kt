package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedWithId
import com.schibsted.spain.barista.internal.matcher.SeekBarAssertions.assertProgress
import com.schibsted.spain.barista.internal.matcher.SeekBarAssertions.assertProgressIsMax
import com.schibsted.spain.barista.internal.matcher.SeekBarAssertions.assertProgressIsMin

object BaristaSeekBarAssertions {

  @JvmStatic
  fun assertProgress(@IdRes seekBarId: Int, expectedProgress: Int) {
    onView(displayedWithId(seekBarId)).check(assertProgress(expectedProgress))
  }

  @JvmStatic
  fun assertProgressIsMin(@IdRes seekBarId: Int) {
    onView(displayedWithId(seekBarId)).check(assertProgressIsMin())
  }

  @JvmStatic
  fun assertProgressIsMax(@IdRes seekBarId: Int) {
    onView(displayedWithId(seekBarId)).check(assertProgressIsMax())
  }
}
