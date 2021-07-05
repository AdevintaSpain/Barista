package com.adevinta.android.barista.sample

import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaContentDescriptionAssertions.assertContentDescription
import com.adevinta.android.barista.assertion.BaristaContentDescriptionAssertions.assertHasContentDescription
import com.adevinta.android.barista.internal.failurehandler.BaristaException
import com.adevinta.android.barista.sample.util.SpyFailureHandlerRule
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.Rule
import org.junit.Test

class ContentDescriptionTest {

  @get:Rule
  var activityRule = ActivityTestRule(ContentDescriptionActivity::class.java)

  @get:Rule
  var spyFailureHandlerRule = SpyFailureHandlerRule()

  @Test
  fun assertContentDescriptionString() {
    assertContentDescription(R.id.imageRed, "Image color red")
    assertContentDescription(R.id.textRed, "Text red")

    spyFailureHandlerRule.assertNoEspressoFailures()
  }

  @Test
  fun assertFailsIfNoContentDescription() {
    val thrown = catchThrowable {
      assertContentDescription(R.id.imageGreen, R.string.content_description_image_color_green)
    }

    spyFailureHandlerRule.assertEspressoFailures(1)

    assertThat(thrown).isInstanceOf(BaristaException::class.java)
            .hasMessage("View (with id: com.adevinta.android.barista.sample:id/imageGreen) " +
                    "didn't match condition (with content description: Image color green)")
  }

  @Test
  fun assertFailsIfNotHasContentDescription() {
    val thrown = catchThrowable {
      assertHasContentDescription(R.id.imageGreen)
    }

    spyFailureHandlerRule.assertEspressoFailures(1)

    assertThat(thrown).isInstanceOf(BaristaException::class.java)
            .hasMessage("View (with id: com.adevinta.android.barista.sample:id/imageGreen) " +
                    "didn't match condition (with content description)")
  }

  @Test
  fun assertContentDescriptionStringResource() {
    assertContentDescription(R.id.imageBlue, R.string.content_description_image_color_blue)
    assertContentDescription(R.id.textBlue, R.string.content_description_text_blue)

    spyFailureHandlerRule.assertNoEspressoFailures()
  }

  @Test
  fun assertViewsHasContentDescription() {
    assertHasContentDescription(R.id.imageRed)
    assertHasContentDescription(R.id.textRed)
    assertHasContentDescription(R.id.imageBlue)
    assertHasContentDescription(R.id.textBlue)

    spyFailureHandlerRule.assertNoEspressoFailures()
  }

}