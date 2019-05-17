package com.schibsted.spain.barista.internal.matcher

import androidx.annotation.DrawableRes
import android.view.View
import com.schibsted.spain.barista.internal.util.BitmapComparator
import com.schibsted.spain.barista.internal.util.DrawableToBitmapConverter
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class BackgroundMatcher private constructor(@DrawableRes private val expectedDrawableRes: Int) : TypeSafeMatcher<View>(View::class.java) {

  companion object {
    private const val EMPTY = -1
    private const val ANY = -2

    @JvmStatic
    fun withBackground(@DrawableRes resourceId: Int) = BackgroundMatcher(resourceId)

    @JvmStatic
    fun withAnyBackground() = BackgroundMatcher(ANY)

    @JvmStatic
    fun withoutBackground() = BackgroundMatcher(EMPTY)
  }

  private var resourceName: String? = null

  override fun matchesSafely(target: View): Boolean {
    val view = target
    if (expectedDrawableRes == EMPTY) {
      return view.background == null
    }
    if (expectedDrawableRes == ANY) {
      return view.background != null
    }
    if (view.background == null) {
      return false
    }
    val resources = target.context.resources
    val expectedDrawable = resources.getDrawable(expectedDrawableRes)
    resourceName = resources.getResourceEntryName(expectedDrawableRes)

    if (expectedDrawable == null) {
      return false
    }

    val viewBitmap = DrawableToBitmapConverter.getBitmap(view.background)
    val expectedBitmap = DrawableToBitmapConverter.getBitmap(expectedDrawable)
    return BitmapComparator.compare(viewBitmap, expectedBitmap)
  }

  override fun describeTo(description: Description) {
    description.appendText("with background from resource id: ")
    description.appendValue(expectedDrawableRes)
    if (resourceName != null) {
      description.appendText("[")
      description.appendText(resourceName)
      description.appendText("]")
    }
  }
}