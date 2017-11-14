package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.v4.widget.SwipeRefreshLayout

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.internal.viewaction.SwipeRefreshActions.pullToRefresh

object BaristaSwipeRefreshInteractions {

    @JvmStatic
    @JvmOverloads
    fun refresh(@IdRes id: Int? = null) = when (id) {
        null -> onView(isAssignableFrom(SwipeRefreshLayout::class.java)).perform(pullToRefresh())
        else -> onView(withId(id)).perform(pullToRefresh())
    }
}
