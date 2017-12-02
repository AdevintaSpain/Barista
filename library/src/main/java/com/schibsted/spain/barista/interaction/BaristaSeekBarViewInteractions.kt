package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import com.schibsted.spain.barista.interaction.BaristaScrollInteractions.safelyScrollTo

object BaristaSeekBarInteractions {
  @JvmStatic
  fun swipeRight(@IdRes seekBarId: Int) {
    safelyScrollTo(seekBarId)
    Espresso.onView(ViewMatchers.withId(seekBarId)).perform(ViewActions.swipeRight())
  }

  @JvmStatic
  fun swipeLeft(@IdRes seekBarId: Int) {
    safelyScrollTo(seekBarId)
    Espresso.onView(ViewMatchers.withId(seekBarId)).perform(ViewActions.swipeLeft())
  }
}
