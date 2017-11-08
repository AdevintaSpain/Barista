package com.schibsted.spain.barista.internal.matcher

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.schibsted.spain.barista.internal.util.BitmapComparator
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Thanks Daniele Bottillo!
 * https://medium.com/@dbottillo/android-ui-test-espresso-matcher-for-imageview-1a28c832626f
 */
class DrawableMatcher(private val expectedId: Int) : TypeSafeMatcher<View>(View::class.java) {

  companion object {
    private val EMPTY = -1
    private val ANY = -2
  }

  private var resourceName: String? = null

  override fun matchesSafely(target: View): Boolean {
    if (target !is ImageView) {
      return false
    }
    val imageView = target
    if (expectedId == EMPTY) {
      return imageView.drawable == null
    }
    if (expectedId == ANY) {
      return imageView.drawable != null
    }
    val resources = target.context.resources
    val expectedDrawable = resources.getDrawable(expectedId)
    resourceName = resources.getResourceEntryName(expectedId)

    if (expectedDrawable == null) {
      return false
    }

    val bitmap = getBitmap(imageView.drawable)
    val otherBitmap = getBitmap(expectedDrawable)
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB_MR1) {
      bitmap.sameAs(otherBitmap)
    } else {
      BitmapComparator.compare(bitmap, otherBitmap)
    }
  }

  private fun getBitmap(drawable: Drawable?): Bitmap {
    val bitmap = Bitmap.createBitmap(drawable!!.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
  }

  override fun describeTo(description: Description) {
    description.appendText("with drawable from resource id: ")
    description.appendValue(expectedId)
    if (resourceName != null) {
      description.appendText("[")
      description.appendText(resourceName)
      description.appendText("]")
    }
  }
}