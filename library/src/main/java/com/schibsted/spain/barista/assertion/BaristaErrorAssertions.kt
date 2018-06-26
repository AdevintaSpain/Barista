package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.design.widget.TextInputLayout
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import android.widget.TextView
import com.schibsted.spain.barista.internal.assertAny
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object BaristaErrorAssertions {

    @JvmStatic
    fun assertError(@IdRes viewId: Int, @StringRes text: Int) {
        val resourceString = InstrumentationRegistry.getTargetContext().resources.getString(text)
        ViewMatchers.withId(viewId).assertAny(withLayoutError(resourceString))
    }

    @JvmStatic
    fun assertError(@IdRes viewId: Int, text: String) {
        ViewMatchers.withId(viewId).assertAny(withLayoutError(text))
    }

    private fun withLayoutError(expectedError: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.let {
                    description.appendText(" $expectedError")
                }
            }

            override fun matchesSafely(item: View): Boolean {
                if (item is TextView) {
                    return expectedError == item.error.toString()
                } else if (item is TextInputLayout) {
                    return expectedError == item.error.toString()
                } else {
                    return false
                }
            }
        }
    }
}