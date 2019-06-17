package com.schibsted.spain.barista.sample

import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaContentDescriptionAssertions.assertContentDescription
import org.junit.Rule
import org.junit.Test

class ContentDescriptionTest {

  @get:Rule
  var activityRule = ActivityTestRule(ContentDescriptionActivity::class.java)

  @Test
  fun assertContentDescriptionString() {
    assertContentDescription(R.id.imageRed, "Image color red")
    assertContentDescription(R.id.textRed, "Text red")
  }
  @Test
  fun assertContentDescriptionStringResource() {
    assertContentDescription(R.id.imageBlue, R.string.content_description_image_color_blue)
    assertContentDescription(R.id.textBlue, R.string.content_description_text_blue)
  }

}