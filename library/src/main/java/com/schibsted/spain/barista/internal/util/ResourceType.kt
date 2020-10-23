package com.schibsted.spain.barista.internal.util

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher

class BaristaResourceTypeException(message: String) : RuntimeException(message)

enum class ResourceType {
  ID, STRING
}

val Int.resourceType: ResourceType
  get() {
    val resourceTypeName = ApplicationProvider.getApplicationContext<Context>().resources.getResourceTypeName(this)
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

enum class ColorResourceType {
  COLOR_RES, COLOR_INT, COLOR_ATTR
}

val Int.colorResourceType: ColorResourceType
  get() {
    return try {
      when (val resourceTypeName = ApplicationProvider.getApplicationContext<Context>().resources.getResourceTypeName(this)) {
        "color" -> ColorResourceType.COLOR_RES
        "attr" -> ColorResourceType.COLOR_ATTR
        else -> throw BaristaResourceTypeException("The argument must be ColorInt or R.color.* or R.attr.*, but was $resourceTypeName")
      }
    } catch (e: Resources.NotFoundException) {
      ColorResourceType.COLOR_INT
    }
  }