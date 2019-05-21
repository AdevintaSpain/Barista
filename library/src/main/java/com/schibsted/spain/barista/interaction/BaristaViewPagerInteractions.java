package com.schibsted.spain.barista.interaction;

import androidx.annotation.IdRes;
import androidx.viewpager.widget.ViewPager;

import static androidx.test.espresso.Espresso.onView;
import static com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedAssignableFrom;
import static com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedWithId;
import static com.schibsted.spain.barista.internal.viewaction.SwipeActions.swipeLeft;
import static com.schibsted.spain.barista.internal.viewaction.SwipeActions.swipeRight;

public class BaristaViewPagerInteractions {

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
