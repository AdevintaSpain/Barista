package com.adevinta.android.barista.internal.viewaction

import android.view.View
import android.view.ViewConfiguration
import androidx.core.view.ViewCompat
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.PrecisionDescriber
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.action.Swiper
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.util.HumanReadables
import androidx.viewpager2.widget.ViewPager2
import com.adevinta.android.barista.internal.viewaction.SwipeActions.swipeLeft
import com.adevinta.android.barista.internal.viewaction.SwipeActions.swipeRight
import com.adevinta.android.barista.internal.viewaction.ViewPager2SwipeAction.Direction.BACKWARD
import com.adevinta.android.barista.internal.viewaction.ViewPager2SwipeAction.Direction.FORWARD
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf

object SwipeActions {

  /**
   * @see android.support.test.espresso.action.ViewActions.EDGE_FUZZ_FACTOR
   */
  private const val EDGE_FUZZ_FACTOR = 0.083f
  private const val CENTER = 1 / 2f
  private const val LEFT = 0f
  private const val RIGHT = 1f
  private const val ALMOST_LEFT = EDGE_FUZZ_FACTOR
  private const val ALMOST_RIGHT = 1 - EDGE_FUZZ_FACTOR

  @JvmStatic
  fun swipeRight(): ViewAction {
    return swipe(from = Pair(ALMOST_LEFT, CENTER), to = Pair(RIGHT, CENTER))
  }

  @JvmStatic
  fun swipeLeft(): ViewAction {
    return swipe(from = Pair(ALMOST_RIGHT, CENTER), to = Pair(LEFT, CENTER))
  }

  private fun swipe(from: Pair<Float, Float>, to: Pair<Float, Float>): ViewAction =
      ViewActions.actionWithAssertions(GeneralSwipeWithPartiallyVisibleViewAction(
          Swipe.FAST,
          NormalizedLocation(from.first, from.second),
          NormalizedLocation(to.first, to.second),
          Press.FINGER))

  @JvmStatic
  fun swipeViewPager2Forward(): ViewAction = ViewPager2SwipeAction(direction = FORWARD)

  @JvmStatic
  fun swipeViewPager2Backward(): ViewAction = ViewPager2SwipeAction(direction = BACKWARD)
}

private class NormalizedLocation(val normalizedXPosition: Float, val normalizedYPosition: Float) : CoordinatesProvider {
  override fun calculateCoordinates(view: View?): FloatArray {
    view ?: return floatArrayOf(0f, 0f)

    val xy = IntArray(2)
    view.getLocationOnScreen(xy)
    val x = xy[0] + view.width * normalizedXPosition
    val y = xy[1] + view.height * normalizedYPosition
    return floatArrayOf(x, y)
  }
}

/**
 * @see android.support.test.espresso.action.GeneralSwipeAction.VIEW_DISPLAY_PERCENTAGE
 *
 * This class overrides the hardcoded requirement that forces the swipeable view
 * to be 90% displayed on the device.
 */
private class GeneralSwipeWithPartiallyVisibleViewAction(
    private val swiper: Swiper,
    private val startCoordinatesProvider: CoordinatesProvider,
    private val endCoordinatesProvider: CoordinatesProvider,
    private val precisionDescriber: PrecisionDescriber
) : ViewAction {

  override fun getConstraints(): Matcher<View> {
    return ViewMatchers.isEnabled()
  }

  override fun perform(uiController: UiController, view: View) {
    val startCoordinates = startCoordinatesProvider.calculateCoordinates(view)
    val endCoordinates = endCoordinatesProvider.calculateCoordinates(view)
    val precision = precisionDescriber.describePrecision()

    var status: Swiper.Status = Swiper.Status.FAILURE

    var tries = 0
    while (tries < MAX_TRIES && status != Swiper.Status.SUCCESS) {
      try {
        status = swiper.sendSwipe(uiController, startCoordinates, endCoordinates, precision)
      } catch (re: RuntimeException) {
        throw PerformException.Builder()
            .withActionDescription(this.description)
            .withViewDescription(HumanReadables.describe(view))
            .withCause(re)
            .build()
      }

      val duration = ViewConfiguration.getPressedStateDuration()
      // ensures that all work enqueued to process the swipe has been run.
      if (duration > 0) {
        uiController.loopMainThreadForAtLeast(duration.toLong())
      }
      tries++
    }

    if (status == Swiper.Status.FAILURE) {
      throw PerformException.Builder()
          .withActionDescription(description)
          .withViewDescription(HumanReadables.describe(view))
          .withCause(RuntimeException(String.format(
              "Couldn't swipe from: %s,%s to: %s,%s precision: %s, %s ." +
                  " Swiper: %s " + "start coordinate provider: %s precision" +
                  " describer: %s. Tried %s times",
              startCoordinates[0],
              startCoordinates[1],
              endCoordinates[0],
              endCoordinates[1],
              precision[0],
              precision[1],
              swiper,
              startCoordinatesProvider,
              precisionDescriber,
              MAX_TRIES)))
          .build()
    }
  }

  override fun getDescription(): String {
    return swiper.toString().toLowerCase() + " swipe"
  }

  companion object {
    /** Maximum number of times to attempt sending a swipe action.  */
    private const val MAX_TRIES = 3
  }
}

/**
 * This class will find a [ViewPager2] and perform the correct swipe given [direction] based on the
 * current [ViewPager2.Orientation] and the right-to-left layout direction.
 */
private class ViewPager2SwipeAction(private val direction: Direction) : ViewAction {

  enum class Direction {
    FORWARD,
    BACKWARD
  }

  override fun getDescription(): String = "Swiping ViewPager2 $direction"

  override fun getConstraints(): Matcher<View> = allOf(anyOf(
      isAssignableFrom(ViewPager2::class.java),
      isDescendantOfA(isAssignableFrom(ViewPager2::class.java))
  ))

  override fun perform(uiController: UiController, view: View) {
    val viewPager = if (view is ViewPager2) {
      view
    } else {
      var parent = view.parent
      while (parent !is ViewPager2 && parent != null) {
        parent = parent.parent
      }
      parent as ViewPager2
    }

    val isForward = direction == FORWARD
    val swipeAction: ViewAction = if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
      if (isForward == viewPager.isRtl()) swipeRight() else swipeLeft()
    } else {
      if (isForward) swipeUp() else swipeDown()
    }
    swipeAction.perform(uiController, view)
  }

  private fun ViewPager2.isRtl(): Boolean {
    return ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL
  }
}
