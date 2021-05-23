package com.schibsted.spain.barista.sample

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerBack
import com.schibsted.spain.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerForward
import com.schibsted.spain.barista.sample.ViewPager2Activity.Orientation.HORIZONTAL
import com.schibsted.spain.barista.sample.ViewPager2Activity.Orientation.VERTICAL
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This test class will cover swiping forward/backward, up/down, and check that it doesn't crash
 * when trying to swipe when on the last page for all four directions. It will be repeated for the
 * API's with and without passing a view id.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class ViewPager2Test {

  @get:Rule
  var activityRule = ActivityTestRule(ViewPager2Activity::class.java, true)

  @Test
  fun checkHorizontalSwipeForward() {
    setViewPager(orientation = HORIZONTAL)
    swipeViewPagerForward()
    assertDisplayed("2")
  }

  @Test
  fun checkHorizontalSwipeForward_withId() {
    setViewPager(orientation = HORIZONTAL)
    swipeViewPagerForward(R.id.viewPager2)
    assertDisplayed("2")
  }

  @Test
  fun checkHorizontalSwipeBack() {
    setViewPager(orientation = HORIZONTAL)
    swipeViewPagerForward()
    swipeViewPagerBack()
    assertDisplayed("1")
  }

  @Test
  fun checkHorizontalSwipeBack_withId() {
    setViewPager(orientation = HORIZONTAL)
    swipeViewPagerForward(R.id.viewPager2)
    swipeViewPagerBack(R.id.viewPager2)
    assertDisplayed("1")
  }

  @Test
  fun checkVerticalSwipeForward() {
    setViewPager(orientation = VERTICAL)
    swipeViewPagerForward()
    assertDisplayed("2")
  }

  @Test
  fun checkVerticalSwipeForward_withId() {
    setViewPager(orientation = VERTICAL)
    swipeViewPagerForward(R.id.viewPager2)
    assertDisplayed("2")
  }

  @Test
  fun checkVerticalSwipeBack() {
    setViewPager(orientation = VERTICAL)
    swipeViewPagerForward()
    swipeViewPagerBack()
    assertDisplayed("1")
  }

  @Test
  fun checkVerticalSwipeBack_withId() {
    setViewPager(orientation = VERTICAL)
    swipeViewPagerForward(R.id.viewPager2)
    swipeViewPagerBack(R.id.viewPager2)
    assertDisplayed("1")
  }

  @Test
  fun swipingForwardHorizontallyWhileAtTheLastPageDoesNotCrash() {
    setViewPager(orientation = HORIZONTAL)
    swipeViewPagerForward()
    swipeViewPagerForward()
    assertDisplayed("2")
  }

  @Test
  fun swipingForwardHorizontallyWhileAtTheLastPageDoesNotCrash_withId() {
    setViewPager(orientation = HORIZONTAL)
    swipeViewPagerForward(R.id.viewPager2)
    swipeViewPagerForward(R.id.viewPager2)
    assertDisplayed("2")
  }

  @Test
  fun swipingBackHorizontallyWhileAtTheFirstPageDoesNotCrash() {
    setViewPager(orientation = HORIZONTAL)
    swipeViewPagerBack()
    assertDisplayed("1")
  }

  @Test
  fun swipingBackHorizontallyWhileAtTheFirstPageDoesNotCrash_withId() {
    setViewPager(orientation = HORIZONTAL)
    swipeViewPagerBack(R.id.viewPager2)
    assertDisplayed("1")
  }

  @Test
  fun swipingForwardVerticallyWhileAtTheLastPageDoesNotCrash() {
    setViewPager(orientation = VERTICAL)
    swipeViewPagerForward()
    swipeViewPagerForward()
    assertDisplayed("2")
  }

  @Test
  fun swipingForwardVerticallyWhileAtTheLastPageDoesNotCrash_withId() {
    setViewPager(orientation = VERTICAL)
    swipeViewPagerForward(R.id.viewPager2)
    swipeViewPagerForward(R.id.viewPager2)
    assertDisplayed("2")
  }

  @Test
  fun swipingBackVerticallyWhileAtTheFirstPageDoesNotCrash() {
    setViewPager(orientation = VERTICAL)
    swipeViewPagerBack()
    assertDisplayed("1")
  }

  @Test
  fun swipingBackVerticallyWhileAtTheFirstPageDoesNotCrash_withId() {
    setViewPager(orientation = VERTICAL)
    swipeViewPagerBack(R.id.viewPager2)
    assertDisplayed("1")
  }

  /** Helper function */
  private fun setViewPager(orientation: ViewPager2Activity.Orientation) {
    activityRule.activity.set(orientation = orientation)
  }
}
