package com.schibsted.spain.barista.assertion

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher.Companion.withAnyBackground
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher.Companion.withBackground
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher.Companion.withoutBackground
import com.schibsted.spain.barista.internal.util.resourceMatcher

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