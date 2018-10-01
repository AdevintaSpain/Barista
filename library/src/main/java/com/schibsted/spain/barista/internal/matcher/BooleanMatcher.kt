package com.schibsted.spain.barista.internal.matcher

import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class BooleanMatcher<T : View>(private val block: (T) -> Boolean) : TypeSafeMatcher<T>() {
    override fun describeTo(description: Description) {
        description.appendText(this::class.java.name).appendText(" matches provided condition")
    }

    override fun matchesSafely(item: T): Boolean = (item as? T)?.let(block) ?: false
}