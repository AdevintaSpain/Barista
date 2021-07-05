package com.adevinta.android.barista.assertion

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import com.adevinta.android.barista.internal.matcher.DisplayedMatchers.displayedWithId
import com.adevinta.android.barista.internal.matcher.RecyclerViewItemCountAssertion

object BaristaRecyclerViewAssertions {

  @JvmStatic
  fun assertRecyclerViewItemCount(@IdRes recyclerViewId: Int, expectedItemCount: Int) {
    onView(displayedWithId(recyclerViewId)).check(RecyclerViewItemCountAssertion(expectedItemCount))
  }
}