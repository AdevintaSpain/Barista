package com.schibsted.spain.barista;

import android.support.annotation.IdRes;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId;

public class BaristaViewPagerActions {

  public static void swipeViewPagerForward(@IdRes int id) {
    onView(displayedWithId(id)).perform(swipeLeft());
  }

  public static void swipeViewPagerBack(@IdRes int id) {
    onView(displayedWithId(id)).perform(swipeRight());
  }
}
