package com.schibsted.spain.barista.interaction

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.schibsted.spain.barista.internal.viewaction.SwipeRefreshActions.pullToRefresh

object BaristaSwipeRefreshInteractions {

  @JvmStatic
  @JvmOverloads
  fun refresh(@IdRes id: Int? = null) = when (id) {
    null -> onView(isAssignableFrom(androidx.swiperefreshlayout.widget.SwipeRefreshLayout::class.java)).perform(pullToRefresh())
    else -> onView(withId(id)).perform(pullToRefresh())
  }
}
