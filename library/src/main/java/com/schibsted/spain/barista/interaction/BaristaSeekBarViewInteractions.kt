package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import com.schibsted.spain.barista.interaction.BaristaScrollInteractions.safelyScrollTo
import com.schibsted.spain.barista.internal.viewaction.SeekBarActions.setProgress
import com.schibsted.spain.barista.internal.viewaction.SeekBarActions.setProgressToMax
import com.schibsted.spain.barista.internal.viewaction.SeekBarActions.setProgressToMin

object BaristaSeekBarInteractions {
  @JvmStatic
  fun setProgress(@IdRes seekBarId: Int, progress: Int) {
    safelyScrollTo(seekBarId)
    Espresso.onView(ViewMatchers.withId(seekBarId)).perform(setProgress(progress))
  }

  @JvmStatic
  fun setProgressToMin(@IdRes seekBarId: Int) {
    safelyScrollTo(seekBarId)
    Espresso.onView(ViewMatchers.withId(seekBarId)).perform(setProgressToMin())
  }

  @JvmStatic
  fun setProgressToMax(@IdRes seekBarId: Int) {
    safelyScrollTo(seekBarId)
    Espresso.onView(ViewMatchers.withId(seekBarId)).perform(setProgressToMax())
  }
}
