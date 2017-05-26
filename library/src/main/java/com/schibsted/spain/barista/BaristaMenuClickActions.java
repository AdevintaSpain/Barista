package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.action.ViewActions;

import static android.support.test.espresso.Espresso.onView;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithText;

public class BaristaMenuClickActions {

  public static void menuClick(@IdRes int id) {
    clickDisplayedView(id);
  }

  public static void menuClick(String text) {
    clickDisplayedView(text);
  }

  private static void clickDisplayedView(@IdRes int id) {
    onView(displayedWithId(id)).perform(ViewActions.click());
  }

  private static void clickDisplayedView(String text) {
    onView(displayedWithText(text)).perform(ViewActions.click());
  }
}
