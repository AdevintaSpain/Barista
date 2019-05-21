package com.schibsted.spain.barista.interaction

import androidx.annotation.IdRes
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.internal.performAction
import com.schibsted.spain.barista.internal.viewaction.RatingBarActions.setRatingBarRatingTo
import com.schibsted.spain.barista.internal.viewaction.RatingBarActions.setRatingBarRatingToMax
import com.schibsted.spain.barista.internal.viewaction.RatingBarActions.setRatingBarRatingToMin

object BaristaRatingBarInteractions {

    @JvmStatic
    fun setRatingTo(@IdRes seekBarId: Int, progress: Int) {
        withId(seekBarId).performAction(setRatingBarRatingTo(progress.toFloat()))
    }

    @JvmStatic
    fun setRatingTo(@IdRes seekBarId: Int, progress: Float) {
        withId(seekBarId).performAction(setRatingBarRatingTo(progress))
    }

    @JvmStatic
    fun setRatingToMin(@IdRes seekBarId: Int) {
        withId(seekBarId).performAction(setRatingBarRatingToMin())
    }

    @JvmStatic
    fun setRatingToMax(@IdRes seekBarId: Int) {
        withId(seekBarId).performAction(setRatingBarRatingToMax())
    }
}
