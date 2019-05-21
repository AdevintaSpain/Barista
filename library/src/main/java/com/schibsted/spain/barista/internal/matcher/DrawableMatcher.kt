package com.schibsted.spain.barista.internal.matcher

import androidx.annotation.DrawableRes
import android.view.View
import android.widget.ImageView
import com.schibsted.spain.barista.internal.util.BitmapComparator
import com.schibsted.spain.barista.internal.util.DrawableToBitmapConverter
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Thanks Daniele Bottillo!
 * https://medium.com/@dbottillo/android-ui-test-espresso-matcher-for-imageview-1a28c832626f
 */
class DrawableMatcher private constructor(@DrawableRes private val expectedDrawableRes: Int) : TypeSafeMatcher<View>(View::class.java) {

  companion object {
    private const val EMPTY = -1
    private const val ANY = -2

    @JvmStatic
    fun withDrawable(@DrawableRes resourceId: Int) = DrawableMatcher(resourceId)

    @JvmStatic
    fun withAnyDrawable() = DrawableMatcher(ANY)

    @JvmStatic
    fun withoutDrawable() = DrawableMatcher(EMPTY)
  }

  private var resourceName: String? = null

  override fun matchesSafely(target: View): Boolean {
    if (target !is ImageView) {
      return false
    }
    val imageView = target
    if (expectedDrawableRes == EMPTY) {
      return imageView.drawable == null
    }
    if (expectedDrawableRes == ANY) {
      return imageView.drawable != null
    }
    if (imageView.drawable == null) {
      return false
    }
    val resources = target.context.resources
    val expectedDrawable = resources.getDrawable(expectedDrawableRes)
    resourceName = resources.getResourceEntryName(expectedDrawableRes)

    if (expectedDrawable == null) {
      return false
    }

    val viewBitmap = DrawableToBitmapConverter.getBitmap(imageView.drawable)
    val expectedBitmap = DrawableToBitmapConverter.getBitmap(expectedDrawable)
    return BitmapComparator.compare(viewBitmap, expectedBitmap)
  }

  override fun describeTo(description: Description) {
    description.appendText("with drawable from resource id: ")
    description.appendValue(expectedDrawableRes)
    if (resourceName != null) {
      description.appendText("[")
      description.appendText(resourceName)
      description.appendText("]")
    }
  }
}