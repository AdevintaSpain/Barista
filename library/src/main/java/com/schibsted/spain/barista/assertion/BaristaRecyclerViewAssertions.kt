package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedWithId
import com.schibsted.spain.barista.internal.matcher.RecyclerViewItemCountAssertion

object BaristaRecyclerViewAssertions {

    @JvmStatic
    fun assertRecyclerViewItemCount(@IdRes recyclerViewId: Int, expectedItemCount: Int) {
        onView(displayedWithId(recyclerViewId)).check(RecyclerViewItemCountAssertion(expectedItemCount))
    }

}