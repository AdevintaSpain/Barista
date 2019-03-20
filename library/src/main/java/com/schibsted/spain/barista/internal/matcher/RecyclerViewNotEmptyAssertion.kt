package com.schibsted.spain.barista.internal.matcher

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not

class RecyclerViewNotEmptyAssertion : ViewAssertion {

  override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
    if (noViewFoundException != null) {
      throw noViewFoundException
    }

    val recyclerView = view as RecyclerView
    val adapter = recyclerView.adapter
    val recyclerViewItemCount = adapter.itemCount
    assertThat<Int>("Recycler item count mismatch. ", recyclerViewItemCount, not<Int>(0))
  }
}