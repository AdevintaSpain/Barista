package com.schibsted.spain.barista.interaction

import androidx.annotation.IdRes
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.internal.performAction
import com.schibsted.spain.barista.internal.viewaction.SeekBarActions.setSeekBarProgressTo
import com.schibsted.spain.barista.internal.viewaction.SeekBarActions.setSeekBarProgressToMax
import com.schibsted.spain.barista.internal.viewaction.SeekBarActions.setSeekBarProgressToMin

object BaristaSeekBarInteractions {
  @JvmStatic
  fun setProgressTo(@IdRes seekBarId: Int, progress: Int) {
    withId(seekBarId).performAction(setSeekBarProgressTo(progress))
  }

  @JvmStatic
  fun setProgressToMin(@IdRes seekBarId: Int) {
    withId(seekBarId).performAction(setSeekBarProgressToMin())
  }

  @JvmStatic
  fun setProgressToMax(@IdRes seekBarId: Int) {
    withId(seekBarId).performAction(setSeekBarProgressToMax())
  }
}
