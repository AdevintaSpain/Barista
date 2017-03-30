package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.contrib.RecyclerViewActions;

import com.schibsted.spain.barista.exception.BaristaException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.custom.ClickChildAction.clickChildWithId;
import static com.schibsted.spain.barista.custom.ClickRootAction.clickRoot;

public class BaristaRecyclerViewActions {

  public static void clickRecyclerViewItem(@IdRes int recyclerViewId, int... positions) {
    if (positions.length == 0) {
      throw new BaristaException("positions cannot be empty");
    }
    for (int p : positions) {
      performClick(recyclerViewId, p);
    }
  }

  private static void performClick(@IdRes int recyclerViewId, int position) {
    onView(withId(recyclerViewId)).perform(
        RecyclerViewActions.actionOnItemAtPosition(position, clickRoot()));
  }

  public static void scrollTo(int recyclerViewId, int position) {
    onView(withId(recyclerViewId)).perform(scrollToPosition(position));
  }

  public static void clickRecyclerAdapterItem(@IdRes int recyclerViewId, int position, @IdRes int itemToClickId) {
    onView(withId(recyclerViewId)).perform(
        RecyclerViewActions.actionOnItemAtPosition(position, clickChildWithId(itemToClickId)));
  }
}
