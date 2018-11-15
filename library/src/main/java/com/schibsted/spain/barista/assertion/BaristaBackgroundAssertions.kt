@file:JvmName("BaristaBackgroundAssertions")

package com.schibsted.spain.barista.assertion

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher.Companion.withAnyBackground
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher.Companion.withBackground
import com.schibsted.spain.barista.internal.matcher.BackgroundMatcher.Companion.withoutBackground
import com.schibsted.spain.barista.internal.util.resourceMatcher

fun assertHasBackground(@IdRes viewId: Int, @DrawableRes drawable: Int) {
  viewId.resourceMatcher().assertAny(withBackground(drawable))
}

fun assertHasAnyBackground(@IdRes viewId: Int) {
  viewId.resourceMatcher().assertAny(withAnyBackground())
}

fun assertHasNoBackground(@IdRes viewId: Int) {
  viewId.resourceMatcher().assertAny(withoutBackground())
}
