package com.adevinta.android.barista.internal.matcher

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import com.adevinta.android.barista.internal.util.BitmapComparator
import com.adevinta.android.barista.internal.util.DrawableToBitmapConverter
import com.google.android.material.button.MaterialButton
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
    val context = target.context
    if (target !is ImageView && target !is MaterialButton) {
      return false
    }
    val drawable = target.getTargetDrawable()
    if (expectedDrawableRes == EMPTY) {
      return drawable == null
    }
    if (expectedDrawableRes == ANY) {
      return drawable != null
    }
    if (drawable == null) {
      return false
    }
    val resources = context.resources

    resourceName = resources.getResourceEntryName(expectedDrawableRes)

    val viewBitmap = DrawableToBitmapConverter.getBitmap(drawable)
    return target.getExpectedBitmap()?.let { BitmapComparator.compare(viewBitmap, it) } ?: false
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

  private fun View.getTargetDrawable(): Drawable? {
    return when (this) {
      is MaterialButton -> this.icon
      is ImageView -> this.drawable
      else -> error("View not supported: $this")
    }
  }

  private fun View.getExpectedBitmap(): Bitmap? {
    return AppCompatResources.getDrawable(context, expectedDrawableRes)?.let { drawable ->
      when (this) {
        is MaterialButton -> DrawableToBitmapConverter.getBitmap(setTargetDrawableTint(drawable))
        is ImageView -> DrawableToBitmapConverter.getBitmap(drawable)
        else -> error("")
      }
    }
  }

  private fun MaterialButton.setTargetDrawableTint(drawable: Drawable): Drawable {
    DrawableCompat.setTint(drawable, this.iconTint.getColorForState(icon.state, Color.BLACK))
    if (iconTintMode != null) DrawableCompat.setTintMode(drawable, iconTintMode)
    return drawable
  }
}
