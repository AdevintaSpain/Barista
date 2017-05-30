package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithDescription;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithText;
import static com.schibsted.spain.barista.custom.HelperMatchers.menuMatcher;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.Is.is;

public class BaristaMenuClickActions {

  public static void clickMenu(@IdRes int id) {
    try {
      clickDisplayedView(id);
    } catch (NoMatchingViewException noMatchingViewException) {
      clickOverflowListMenu(id);
    }
  }

  public static void clickMenu(String text) {
    try {
      clickDisplayedView(text);
    } catch (NoMatchingViewException noMatchingViewException) {
      try {
        clickViewWithDescription(text);
      } catch (NoMatchingViewException noMatchingViewByTextException) {
        clickOverflowListMenu(text);
      }
    }
  }

  private static void clickDisplayedView(String text) {
    onView(displayedWithText(text)).perform(click());
  }

  private static void clickDisplayedView(@IdRes int id) {
    onView(displayedWithId(id)).perform(click());
  }

  private static void clickViewWithDescription(String text) {
    onView(displayedWithDescription(text)).perform(click());
  }

  private static void clickOverflowListMenu(@IdRes int id) {
    openOverflow();
    onData(menuMatcher(id)).inRoot(isPlatformPopup()).perform(click());
  }

  private static void clickOverflowListMenu(String text) {
    openOverflow();
    onData(hasToString(is(text))).inRoot(isPlatformPopup()).perform(click());
  }

  private static void openOverflow() {
    openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
  }
}
