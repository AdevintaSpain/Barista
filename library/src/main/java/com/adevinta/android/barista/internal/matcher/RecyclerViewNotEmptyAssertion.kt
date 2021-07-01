package com.adevinta.android.barista.internal.matcher

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not

class RecyclerViewNotEmptyAssertion : ViewAssertion {

  override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
    if (noViewFoundException != null) {
      throw noViewFoundException
    }

    val recyclerView = view as RecyclerView
    val adapter = recyclerView.adapter
    val recyclerViewItemCount = adapter?.itemCount ?: 0
    assertThat<Int>("Recycler item count mismatch. ", recyclerViewItemCount, not<Int>(0))
  }
}