package com.schibsted.spain.barista.assertion

import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.matcher.BitmapDrawableMatcher.Companion.withBitmap
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withAnyDrawable
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withoutDrawable

object BaristaImageViewAssertions {

    @JvmStatic
    fun assertHasBitmap(@IdRes imageViewId: Int, bitmap: Bitmap) {
        withId(imageViewId).assertAny(withBitmap(bitmap))
    }

    @JvmStatic
    fun assertHasDrawable(@IdRes imageViewId: Int, @DrawableRes drawable: Int) {
        withId(imageViewId).assertAny(withDrawable(drawable))
    }

    @JvmStatic
    fun assertHasAnyDrawable(@IdRes imageViewId: Int) {
        withId(imageViewId).assertAny(withAnyDrawable())
    }

    @JvmStatic
    fun assertHasNoDrawable(@IdRes imageViewId: Int) {
        withId(imageViewId).assertAny(withoutDrawable())
    }
}
