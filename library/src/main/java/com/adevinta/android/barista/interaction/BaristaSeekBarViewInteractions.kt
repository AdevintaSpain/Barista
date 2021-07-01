package com.adevinta.android.barista.interaction

import androidx.annotation.IdRes
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.internal.performAction
import com.adevinta.android.barista.internal.viewaction.SeekBarActions.setSeekBarProgressTo
import com.adevinta.android.barista.internal.viewaction.SeekBarActions.setSeekBarProgressToMax
import com.adevinta.android.barista.internal.viewaction.SeekBarActions.setSeekBarProgressToMin

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
