package com.adevinta.android.barista.assertion

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import com.adevinta.android.barista.internal.assertAnyView
import com.adevinta.android.barista.internal.failurehandler.RethrowingFailureHandler
import com.adevinta.android.barista.internal.matcher.BooleanMatcher
import com.adevinta.android.barista.internal.matcher.withCompatText
import com.adevinta.android.barista.internal.util.resourceMatcher
import org.hamcrest.Matcher
import org.junit.Assert.fail

object BaristaAssertions {

  @JvmStatic
  fun assertThatBackButtonClosesTheApp() {
    // Will launch an Exception if it closes the app
    try {
      onView(isRoot())
          .withFailureHandler(RethrowingFailureHandler())
          .perform(ViewActions.pressBack())
      // One of our Activities is appearing on the screen :(
      fail("The back button didn't close the app")
    } catch (noActivityException: NoActivityResumedException) {
      //Yey!
    }
  }

  /**
   * Extension function alias for [assertAnyView]
   */
  @JvmStatic
  inline fun <reified T : View> assertAny(resId: Int, assertionDescription: String? = null, noinline block: (T) -> Boolean) {
    assertAny(resId.resourceMatcher(), assertionDescription, block)
  }

  /**
   * Extension function alias for [assertAnyView]
   */
  @JvmStatic
  inline fun <reified T : View> assertAny(text: String, assertionDescription: String? = null, noinline block: (T) -> Boolean) {
    assertAny(withCompatText(text), assertionDescription, block)
  }

  /**
   * Extension function alias for [assertAnyView]
   */
  @JvmStatic
  inline fun <reified T : View> assertAny(matcher: Matcher<View>, assertionDescription: String? = null, noinline block: (T) -> Boolean) {
    assertAnyView(viewMatcher = matcher, condition = BooleanMatcher(assertionDescription, block) as Matcher<View>)
  }
}
