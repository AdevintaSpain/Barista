package com.schibsted.spain.barista.action

import android.support.annotation.IdRes
import android.support.v4.widget.SwipeRefreshLayout

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.internal.viewaction.SwipeRefreshActions.pullToRefresh

object BaristaSwipeRefreshActions {

    @JvmStatic
    @JvmOverloads
    fun refresh(@IdRes swipeRefreshId: Int? = null) = when (swipeRefreshId) {
        null -> onView(isAssignableFrom(SwipeRefreshLayout::class.java)).perform(pullToRefresh())
        else -> onView(withId(swipeRefreshId)).perform(pullToRefresh())
    }
}
