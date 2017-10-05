package com.schibsted.spain.barista.internal

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import com.schibsted.spain.barista.exception.BaristaArgumentTypeException
import org.hamcrest.Matcher

enum class ResourceType {
    ID, STRING
}

val Int.resourceType: ResourceType
    get() {
        val resourceTypeName = InstrumentationRegistry.getTargetContext().resources.getResourceTypeName(this)
        return when (resourceTypeName) {
            "id" -> ResourceType.ID
            "string" -> ResourceType.STRING
            else -> throw BaristaArgumentTypeException()
        }
    }

fun Int.resourceMatcher(): Matcher<View> = when (resourceType) {
    ResourceType.ID -> withId(this)
    ResourceType.STRING -> withText(this)
}
