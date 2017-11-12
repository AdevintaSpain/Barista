package com.schibsted.spain.barista.action;

import android.support.annotation.IdRes;
import android.support.v4.widget.SwipeRefreshLayout;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.internal.viewaction.SwipeRefreshActions.pullToRefresh;

public class BaristaSwipeRefreshActions {

  public static void refresh() {
    onView(isAssignableFrom(SwipeRefreshLayout.class)).perform(pullToRefresh());
  }

  public static void refresh(@IdRes int swipeRefreshId) {
    onView(withId(swipeRefreshId)).perform(pullToRefresh());
  }
}
