package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId;
import static org.hamcrest.core.AllOf.allOf;

public class BaristaViewPagerActions {

  public static void swipeViewPagerForward(@IdRes int id) {
    onView(displayedWithId(id)).perform(swipeLeft());
  }

  public static void swipeViewPagerBack(@IdRes int id) {
    onView(displayedWithId(id)).perform(swipeRight());
  }

  /*
   * Finds a ViewPager and swipes it forward.
   *
   * It only works if there's only one ViewPager displayed on the screen.
   * If there are more than one, use swipeViewPagerForward(@IdRes int id)
   */
  public static void swipeViewPagerForward() {
    onView(allOf(isDisplayed(), isAssignableFrom(ViewPager.class))).perform(swipeLeft());
  }

  /*
   * Finds a ViewPager and swipes it back.
   *
   * It only works if there's only one ViewPager displayed on the screen.
   * If there are more than one, use swipeViewPagerBack(@IdRes int id)
   */
  public static void swipeViewPagerBack() {
    onView(allOf(isDisplayed(), isAssignableFrom(ViewPager.class))).perform(swipeRight());
  }
}
