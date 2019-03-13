package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.internal.performAction
import com.schibsted.spain.barista.internal.viewaction.RatingBarActions.setRatingBarProgressTo
import com.schibsted.spain.barista.internal.viewaction.RatingBarActions.setRatingBarProgressToMax
import com.schibsted.spain.barista.internal.viewaction.RatingBarActions.setRatingBarProgressToMin

object BaristaRatingBarInteractions {

    @JvmStatic
    fun setRatingTo(@IdRes seekBarId: Int, progress: Int) {
        withId(seekBarId).performAction(setRatingBarProgressTo(progress.toFloat()))
    }

    @JvmStatic
    fun setRatingTo(@IdRes seekBarId: Int, progress: Float) {
        withId(seekBarId).performAction(setRatingBarProgressTo(progress))
    }

    @JvmStatic
    fun setRatingToMin(@IdRes seekBarId: Int) {
        withId(seekBarId).performAction(setRatingBarProgressToMin())
    }

    @JvmStatic
    fun setRatingToMax(@IdRes seekBarId: Int) {
        withId(seekBarId).performAction(setRatingBarProgressToMax())
    }
}
