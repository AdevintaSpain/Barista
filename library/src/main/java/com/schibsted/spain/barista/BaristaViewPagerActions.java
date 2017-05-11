package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedAssignableFrom;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId;

public class BaristaViewPagerActions {

  /*
   * Finds a ViewPager and swipes it forward.
   *
   * It only works if there's only one ViewPager displayed on the screen.
   * If there are more than one, use swipeViewPagerForward(@IdRes int id)
   */
  public static void swipeViewPagerForward() {
    onView(displayedAssignableFrom(ViewPager.class)).perform(swipeLeft());
  }

  public static void swipeViewPagerForward(@IdRes int id) {
    onView(displayedWithId(id)).perform(swipeLeft());
  }

  /*
   * Finds a ViewPager and swipes it back.
   *
   * It only works if there's only one ViewPager displayed on the screen.
   * If there are more than one, use swipeViewPagerBack(@IdRes int id)
   */
  public static void swipeViewPagerBack() {
    onView(displayedAssignableFrom(ViewPager.class)).perform(swipeRight());
  }

  public static void swipeViewPagerBack(@IdRes int id) {
    onView(displayedWithId(id)).perform(swipeRight());
  }
}
