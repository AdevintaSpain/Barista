package com.adevinta.android.barista.assertion

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.internal.assertAny
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withAnyDrawable
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withoutDrawable

object BaristaImageViewAssertions {

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