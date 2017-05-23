package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import com.schibsted.spain.barista.custom.CustomConstraintViewAction;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class BaristaSwipeRefreshActions {

  public static void pullToRefresh(@IdRes int swipeRefreshId) {
    onView(withId(swipeRefreshId)).perform(
        new CustomConstraintViewAction(swipeDown(), isDisplayingAtLeast(80)));
  }
}
