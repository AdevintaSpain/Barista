package com.schibsted.spain.barista.sample

import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.EditText
import android.widget.ImageView
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.failurehandler.BaristaException
import com.schibsted.spain.barista.sample.util.SpyFailureHandlerRule
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.ThrowableAssert.catchThrowable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AssertAnyTest {

    @get:Rule
    var activityRule = ActivityTestRule(EditTextActivity::class.java)

    @get:Rule
    var spyFailureHandlerRule = SpyFailureHandlerRule()

    @Test
    fun checkWriteOnEditText_whenEditTextIsVisible_id() {
        spyFailureHandlerRule.assertNoEspressoFailures()
        writeTo(R.id.edittext, "Hello!")

        assertAny<EditText>(R.id.edittext) {
            it.text.toString() == "Hello!"
        }
    }

    @Test
    fun checkWriteOnEditText_whenEditTextIsVisible_text() {
        spyFailureHandlerRule.assertNoEspressoFailures()
        writeTo(R.id.edittext, "Hello!")

        assertAny<EditText>("Hello!") {
            it.text.toString() == "Hello!"
        }
    }

    @Test
    fun checkWriteOnEditText_whenEditTextIsVisible_matcher() {
        spyFailureHandlerRule.assertNoEspressoFailures()
        writeTo(R.id.edittext, "Hello!")

        assertAny<EditText>(withId(R.id.edittext)) {
            it.text.toString() == "Hello!"
        }
    }

    @Test
    fun checkNoMatchesWriteOnEditText_whenAssertOnDifferentTypeOfView() {
        val thrown = catchThrowable { assertAny<ImageView>(R.id.edittext) { true } }

        spyFailureHandlerRule.assertEspressoFailures(1)

        writeTo(R.id.edittext, "Hello!")

        assertThat(thrown).isInstanceOf(BaristaException::class.java)
                .hasMessageContaining("didn't match condition")
                .hasMessageContaining("(custom condition)")
    }

    @Test
    fun checkNoMatchesWriteOnEditText_whenAssertOnDifferentTypeOfView_customMessage() {
        val thrown = catchThrowable { assertAny<ImageView>(R.id.edittext, "Not an ImageView") { true } }

        spyFailureHandlerRule.assertEspressoFailures(1)

        writeTo(R.id.edittext, "Hello!")

        assertThat(thrown).isInstanceOf(BaristaException::class.java)
                .hasMessageContaining("didn't match condition")
                .hasMessageContaining("Not an ImageView")
    }
}
