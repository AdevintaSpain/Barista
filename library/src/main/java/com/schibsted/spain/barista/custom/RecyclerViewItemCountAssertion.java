package com.schibsted.spain.barista.custom;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RecyclerViewItemCountAssertion implements ViewAssertion {
  private final Matcher<Integer> matcher;

  public RecyclerViewItemCountAssertion(Matcher<Integer> matcher) {
    this.matcher = matcher;
  }

  public RecyclerViewItemCountAssertion(int count) {
    this.matcher = is(count);
  }

  @Override
  public void check(View view, NoMatchingViewException noViewFoundException) {
    if (noViewFoundException != null) {
      throw noViewFoundException;
    }
    RecyclerView recyclerView = (RecyclerView) view;
    RecyclerView.Adapter adapter = recyclerView.getAdapter();
    int recyclerViewItemCount = adapter.getItemCount();
    assertThat(recyclerViewItemCount, matcher);
  }
}