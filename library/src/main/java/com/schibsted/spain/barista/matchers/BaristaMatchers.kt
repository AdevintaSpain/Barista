package com.schibsted.spain.barista.matchers

import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

object BaristaMatchers {

  fun tag(tagValue: String): Matcher<View> {
    return ViewMatchers.withTagValue(CoreMatchers.`is`(tagValue))
  }

}
