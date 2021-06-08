package com.adevinta.android.barista.assertion

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.adevinta.android.barista.internal.assertAny
import com.adevinta.android.barista.internal.matcher.BackgroundMatcher.Companion.withAnyBackground
import com.adevinta.android.barista.internal.matcher.BackgroundMatcher.Companion.withBackground
import com.adevinta.android.barista.internal.matcher.BackgroundMatcher.Companion.withoutBackground
import com.adevinta.android.barista.internal.util.resourceMatcher

object BaristaBackgroundAssertions {

  @JvmStatic
  fun assertHasBackground(@IdRes viewId: Int, @DrawableRes drawable: Int) {
    viewId.resourceMatcher().assertAny(withBackground(drawable))
  }

  @JvmStatic
  fun assertHasAnyBackground(@IdRes viewId: Int) {
    viewId.resourceMatcher().assertAny(withAnyBackground())
  }

  @JvmStatic
  fun assertHasNoBackground(@IdRes viewId: Int) {
    viewId.resourceMatcher().assertAny(withoutBackground())
  }
}