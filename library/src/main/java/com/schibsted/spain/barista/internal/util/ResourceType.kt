package com.schibsted.spain.barista.internal.util

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import org.hamcrest.Matcher

class BaristaResourceTypeException(message: String) : RuntimeException(message)

enum class ResourceType {
    ID, STRING
}

val Int.resourceType: ResourceType
    get() {
        val resourceTypeName = InstrumentationRegistry.getTargetContext().resources.getResourceTypeName(this)
        return when (resourceTypeName) {
            "id" -> ResourceType.ID
            "string" -> ResourceType.STRING
            else -> throw BaristaResourceTypeException("The id argument must be R.id.* or R.string.*, but was $resourceTypeName")
        }
    }

fun Int.resourceMatcher(): Matcher<View> = when (resourceType) {
    ResourceType.ID -> withId(this)
    ResourceType.STRING -> withText(this)
}
