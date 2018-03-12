package com.schibsted.spain.barista.internal.matcher

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import com.schibsted.spain.barista.internal.util.BitmapComparator
import com.schibsted.spain.barista.internal.util.DrawableToBitmapConverter
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Based on [DrawableMatcher] but takes an expected [Bitmap].
 */
class BitmapDrawableMatcher private constructor(private val expectedBitmap: Bitmap) : TypeSafeMatcher<View>(View::class.java) {

  companion object {
    @JvmStatic
    fun withBitmap(bitmap: Bitmap) = BitmapDrawableMatcher(bitmap)
  }

  override fun matchesSafely(target: View): Boolean {
    if (target !is ImageView) {
      return false
    }
    if (target.drawable == null){
      return false
    }

    val viewBitmap = DrawableToBitmapConverter.getBitmap(target.drawable)
    return BitmapComparator.compare(viewBitmap, expectedBitmap)
  }

  override fun describeTo(description: Description) {
    description.appendText("with bitmap: ")
    description.appendValue("${expectedBitmap.width} x ${expectedBitmap.height}")
  }
}
