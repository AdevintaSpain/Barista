package com.schibsted.spain.barista.interaction

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedAssignableFrom
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedWithId
import com.schibsted.spain.barista.internal.viewaction.SwipeActions.swipeLeft
import com.schibsted.spain.barista.internal.viewaction.SwipeActions.swipeRight

object BaristaViewPagerInteractions {

  /*
   * Finds a ViewPager and swipes it forward.
   *
   * It only works if there's only one ViewPager displayed on the screen.
   * If there are more than one, use swipeViewPagerForward(@IdRes int id)
   */
  @JvmStatic
  fun swipeViewPagerForward() {
    val spyFailureHandler = SpyFailureHandler()
    try {
      onView(displayedAssignableFrom(ViewPager::class.java)).perform(swipeLeft())
    } catch (noMatchingViewException: NoMatchingViewException) {
      try {
        onView(displayedAssignableFrom(ViewPager2::class.java)).perform(swipeLeft())
      } catch (error: Exception) {
        spyFailureHandler.resendFirstError("Could not swipe forward the ViewPager")
      }
    }
  }

  @JvmStatic
  fun swipeViewPagerForward(@IdRes id: Int) {
    onView(displayedWithId(id)).perform(swipeLeft())
  }

  /*
   * Finds a ViewPager and swipes it back.
   *
   * It only works if there's only one ViewPager displayed on the screen.
   * If there are more than one, use swipeViewPagerBack(@IdRes int id)
   */
  @JvmStatic
  fun swipeViewPagerBack() {
    val spyFailureHandler = SpyFailureHandler()
    try {
      onView(displayedAssignableFrom(ViewPager::class.java)).perform(swipeRight())
    } catch (noMatchingViewException: NoMatchingViewException) {
      try {
        onView(displayedAssignableFrom(ViewPager2::class.java)).perform(swipeRight())
      } catch (error: Exception) {
        spyFailureHandler.resendFirstError("Could not swipe back the ViewPager")
      }
    }
  }

  @JvmStatic
  fun swipeViewPagerBack(@IdRes id: Int) {
    onView(displayedWithId(id)).perform(swipeRight())
  }
}
