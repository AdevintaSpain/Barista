package com.adevinta.android.barista.sample

import android.widget.EditText
import android.widget.ImageView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaAssertions.assertAny
import com.adevinta.android.barista.interaction.BaristaEditTextInteractions.writeTo
import com.adevinta.android.barista.internal.failurehandler.BaristaException
import com.adevinta.android.barista.sample.util.SpyFailureHandlerRule
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.ThrowableAssert.catchThrowable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class AssertAnyTest {

  @get:Rule
  var activityRule = ActivityTestRule(EditTextActivity::class.java)

  @get:Rule
  var spyFailureHandlerRule = SpyFailureHandlerRule()

  @Test
  fun assertAny_with_idMatcher() {
    spyFailureHandlerRule.assertNoEspressoFailures()
    writeTo(R.id.edittext, "Hello!")

    assertAny<EditText>(R.id.edittext) {
      it.text.toString() == "Hello!"
    }
  }

  @Test
  fun assertAny_with_valid_cast_custom_error() {
    val thrown = catchThrowable {
      assertAny<EditText>(R.id.edittext, "String is not the same") {
        it.text.toString() == "Hello"
      }
    }

    spyFailureHandlerRule.assertEspressoFailures(1)

    writeTo(R.id.edittext, "Hello!")

    assertThat(thrown).isInstanceOf(BaristaException::class.java)
        .hasMessageContaining("didn't match condition (String is not the same)")
  }

  @Test
  fun assertAny_with_textMatcher() {
    spyFailureHandlerRule.assertNoEspressoFailures()
    writeTo(R.id.edittext, "Hello!")

    assertAny<EditText>("Hello!") {
      it.text.toString() == "Hello!"
    }
  }

  @Test
  fun assertAny_with_MatcherCustom() {
    spyFailureHandlerRule.assertNoEspressoFailures()
    writeTo(R.id.edittext, "Hello!")

    assertAny<EditText>(withId(R.id.edittext)) {
      it.text.toString() == "Hello!"
    }
  }

  @Test
  fun assertAny_with_wrong_view_cast_error_default_message() {
    val thrown = catchThrowable { assertAny<ImageView>(R.id.edittext) { true } }

    spyFailureHandlerRule.assertEspressoFailures(1)

    writeTo(R.id.edittext, "Hello!")

    assertThat(thrown).isInstanceOf(BaristaException::class.java)
        .hasMessageContaining(
            "didn't match condition (custom condition [use `assertionDescription` parameter on `assertAny` to setup descriptive message])")
  }

  @Test
  fun assertAny_with_wrong_view_cast_error_custom_message() {
    val thrown = catchThrowable { assertAny<ImageView>(R.id.edittext, "is an ImageView") { true } }

    spyFailureHandlerRule.assertEspressoFailures(1)

    writeTo(R.id.edittext, "Hello!")

    assertThat(thrown).isInstanceOf(BaristaException::class.java)
        .hasMessageContaining("didn't match condition (is an ImageView)")
  }
}
