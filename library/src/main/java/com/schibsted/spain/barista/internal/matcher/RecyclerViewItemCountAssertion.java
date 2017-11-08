package com.schibsted.spain.barista.internal.matcher;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RecyclerViewItemCountAssertion implements ViewAssertion {
  private final int expectedCount;

  public RecyclerViewItemCountAssertion(int expectedCount) {
    this.expectedCount = expectedCount;
  }

  @Override
  public void check(View view, NoMatchingViewException noViewFoundException) {
    if (noViewFoundException != null) {
      throw noViewFoundException;
    }
    RecyclerView recyclerView = (RecyclerView) view;
    RecyclerView.Adapter adapter = recyclerView.getAdapter();
    int recyclerViewItemCount = adapter.getItemCount();
    assertThat(recyclerViewItemCount, is(expectedCount));
  }
}