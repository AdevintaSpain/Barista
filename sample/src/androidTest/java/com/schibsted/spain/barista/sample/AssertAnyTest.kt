package com.schibsted.spain.barista.sample

import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.EditText
import android.widget.ImageView
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.failurehandler.BaristaException
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AssertAnyTest {

    @get:Rule
    var activityRule = ActivityTestRule(EditTextActivity::class.java)

    @Test
    fun checkWriteOnEditText_whenEditTextIsVisible_id() {
        writeTo(R.id.edittext, "Hello!")

        assertAny<EditText>(R.id.edittext) {
            it.text.toString() == "Hello!"
        }
    }

    @Test
    fun checkWriteOnEditText_whenEditTextIsVisible_text() {
        writeTo(R.id.edittext, "Hello!")

        assertAny<EditText>("Hello!") {
            it.text.toString() == "Hello!"
        }
    }

    @Test
    fun checkWriteOnEditText_whenEditTextIsVisible_matcher() {
        writeTo(R.id.edittext, "Hello!")

        assertAny<EditText>(withId(R.id.edittext)) {
            it.text.toString() == "Hello!"
        }
    }

    @Test(expected = BaristaException::class)
    fun checkNoMatchesWriteOnEditText_whenAssertOnDifferentTypeOfView() {
        writeTo(R.id.edittext, "Hello!")

        assertAny<ImageView>(R.id.edittext) { true }
    }
}
