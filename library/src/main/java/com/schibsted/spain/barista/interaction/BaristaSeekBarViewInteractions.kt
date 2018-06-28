package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import com.schibsted.spain.barista.interaction.BaristaScrollInteractions.safelyScrollTo
import com.schibsted.spain.barista.internal.viewaction.SeekBarActions.setSeekBarProgressTo
import com.schibsted.spain.barista.internal.viewaction.SeekBarActions.setSeekBarProgressToMax
import com.schibsted.spain.barista.internal.viewaction.SeekBarActions.setSeekBarProgressToMin

object BaristaSeekBarInteractions {
  @JvmStatic
  fun setProgressTo(@IdRes seekBarId: Int, progress: Int) {
    safelyScrollTo(seekBarId)
    Espresso.onView(ViewMatchers.withId(seekBarId)).perform(setSeekBarProgressTo(progress))
  }

  @JvmStatic
  fun setProgressToMin(@IdRes seekBarId: Int) {
    safelyScrollTo(seekBarId)
    Espresso.onView(ViewMatchers.withId(seekBarId)).perform(setSeekBarProgressToMin())
  }

  @JvmStatic
  fun setProgressToMax(@IdRes seekBarId: Int) {
    safelyScrollTo(seekBarId)
    Espresso.onView(ViewMatchers.withId(seekBarId)).perform(setSeekBarProgressToMax())
  }
}
