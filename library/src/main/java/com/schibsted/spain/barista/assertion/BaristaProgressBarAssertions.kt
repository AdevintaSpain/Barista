@file:JvmName("BaristaProgressBarAssertions")

package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedWithId
import com.schibsted.spain.barista.internal.matcher.ProgressBarMatchers.withMaxProgress
import com.schibsted.spain.barista.internal.matcher.ProgressBarMatchers.withMinProgress
import com.schibsted.spain.barista.internal.matcher.ProgressBarMatchers.withProgress

fun assertProgress(@IdRes progressBarId: Int, expectedProgress: Int) {
  onView(displayedWithId(progressBarId)).check(matches(withProgress(expectedProgress)))
}

fun assertProgressIsMin(@IdRes progressBarId: Int) {
  onView(displayedWithId(progressBarId)).check(matches(withMinProgress()))
}

fun assertProgressIsMax(@IdRes progressBarId: Int) {
  onView(displayedWithId(progressBarId)).check(matches(withMaxProgress()))
}
