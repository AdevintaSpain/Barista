package com.schibsted.spain.barista.sample.assertion

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.matchers.BaristaMatchers.tag
import com.schibsted.spain.barista.sample.SomeViewsWithDifferentVisibilitiesActivity
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TagAssertionsTest {

  @Rule
  @JvmField
  var activityRule =
      ActivityTestRule(SomeViewsWithDifferentVisibilitiesActivity::class.java)

  @Test
  fun tag_name_visible_should_be_displayed() {
    assertDisplayed(tag("visible"))
  }


  @Test
  fun tag_name_invisible_should_not_be_displayed() {
    assertNotDisplayed(tag("invisible"))
  }

  @Test
  fun tag_name_visible_should_fail_when_asserting_not_displayed() {
    try {
      assertNotDisplayed(tag("visible"))
      fail()
    } catch (expected: Throwable) {

    }
  }

  @Test
  fun tag_name_invisible_should_fail_when_asserting_displayed() {
    try {
      assertNotDisplayed(tag("visible"))
      fail()
    } catch (expected: Throwable) {

    }
  }


}
