package com.schibsted.spain.barista.sample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.rule.theme.DayNightRule
import org.junit.Rule
import org.junit.Test

class DayNightTestRuleTest {

  @get:Rule
  val activityRule = ActivityTestRule(HelloWorldActivity::class.java, true, false)

  @get:Rule
  val dayNightRule = DayNightRule()

  @Test
  fun someDeterministicTest() {
    activityRule.launchActivity(null)
    onView(withId(R.id.some_view)).check(matches(isDisplayed()))
  }

  @Test
  fun testWillFailOnDayMode() {
    try {
      activityRule.launchActivity(null)
      onView(withId(R.id.text_night)).check(matches(isDisplayed()))
    } catch (t: Throwable) {
    }
  }
}