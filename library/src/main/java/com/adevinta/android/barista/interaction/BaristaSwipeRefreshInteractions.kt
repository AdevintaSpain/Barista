package com.adevinta.android.barista.interaction

import androidx.annotation.IdRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.internal.viewaction.SwipeRefreshActions.pullToRefresh

object BaristaSwipeRefreshInteractions {

  @JvmStatic
  @JvmOverloads
  fun refresh(@IdRes id: Int? = null) = when (id) {
    null -> onView(isAssignableFrom(SwipeRefreshLayout::class.java)).perform(pullToRefresh())
    else -> onView(withId(id)).perform(pullToRefresh())
  }
}
