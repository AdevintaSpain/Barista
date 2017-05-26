package com.schibsted.spain.barista;

import android.support.annotation.IdRes;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.custom.SwipeRefreshActions.pullToRefresh;

public class BaristaSwipeRefreshActions {

  public static void refresh(@IdRes int swipeRefreshId) {
    onView(withId(swipeRefreshId)).perform(pullToRefresh());
  }
}
