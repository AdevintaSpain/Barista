package com.adevinta.android.barista.internal.matcher

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.annotation.DrawableRes
import android.view.View
import com.adevinta.android.barista.internal.util.BitmapComparator
import com.adevinta.android.barista.internal.util.DrawableToBitmapConverter
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

    return when (expectedDrawable) {
      is ColorDrawable -> isSameColorDrawable(view.background as ColorDrawable, expectedDrawable)
      is GradientDrawable -> isSameGradientDrawable(view.background as GradientDrawable, expectedDrawable)
      else -> hasSameBitmap(view, expectedDrawable)
    }
  }

  private fun hasSameBitmap(view: View, expectedDrawable: Drawable?): Boolean {
    val viewBitmap = DrawableToBitmapConverter.getBitmap(view.background)
    val expectedBitmap = DrawableToBitmapConverter.getBitmap(expectedDrawable)
    return BitmapComparator.compare(viewBitmap, expectedBitmap)
  }

  private fun isSameColorDrawable(viewDrawable: ColorDrawable, expectedDrawable: ColorDrawable) =
    viewDrawable.color == expectedDrawable.color &&
        viewDrawable.alpha == expectedDrawable.alpha &&
        viewDrawable.opacity == expectedDrawable.opacity

  private fun isSameGradientDrawable(viewDrawable: GradientDrawable, expectedDrawable: GradientDrawable): Boolean {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
      viewDrawable.alpha == expectedDrawable.alpha &&
          viewDrawable.opacity == expectedDrawable.opacity &&
          viewDrawable.color == expectedDrawable.color &&
          viewDrawable.shape == expectedDrawable.shape
    } else {
      viewDrawable.alpha == expectedDrawable.alpha &&
          viewDrawable.opacity == expectedDrawable.opacity
    }
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
