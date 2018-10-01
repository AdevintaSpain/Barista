package com.schibsted.spain.barista.sample

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.EditText
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import com.schibsted.spain.barista.internal.assertAny
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AssertAnyTest {

    @get:Rule
    var activityRule = ActivityTestRule(EditTextActivity::class.java)

    @Test
    fun checkWriteOnEditText_whenEditTextIsVisible() {
        writeTo(R.id.edittext, "Hello!")

        assertAny<EditText>(R.id.edittext) {
            it.text.toString() == "Hello!"
        }
        assertAny<EditText>("Hello!") {
            it.text.toString() == "Hello!"
        }
    }
}
