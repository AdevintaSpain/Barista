package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.action.ViewActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithText;

public class BaristaMenuClickActions {

  public static void menuClick(@IdRes int id) {
    try {
      clickDisplayedView(id);
    } catch (NoMatchingViewException noMatchingViewexception) {
      openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
      clickDisplayedView(id);
    }
  }

  public static void menuClick(String text) {
    try {
      clickDisplayedView(text);
    } catch (NoMatchingViewException noMatchingViewexception) {
      openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
      clickDisplayedView(text);
    }
  }

  public static void menuClick(@IdRes int id, String text) {
    try {
      clickDisplayedView(id);
    } catch (NoMatchingViewException noMatchingViewException) {
      try {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        clickDisplayedView(id);
      } catch (NoMatchingViewException noMatchingViewException2) {
        menuClick(text);
      }
    }
  }

  private static void clickDisplayedView(@IdRes int id) {
    onView(displayedWithId(id)).perform(ViewActions.click());
  }

  private static void clickDisplayedView(String text) {
    onView(displayedWithText(text)).perform(ViewActions.click());
  }
}
