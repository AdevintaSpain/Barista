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

  public static void menuClick(@IdRes int id) {
    try {
      clickDisplayedView(id);
    } catch (NoMatchingViewException noMatchingViewException) {
      openOverflow();
      onData(menuMatcher(id)).inRoot(isPlatformPopup()).perform(click());
    }
  }

  public static void menuClick(String text) {
    try {
      clickDisplayedView(text);
    } catch (NoMatchingViewException noMatchingViewException) {
      try {
        onView(displayedWithDescription(text)).perform(click());
      } catch (NoMatchingViewException noMatchingViewByTextException) {
        openOverflow();
        onData(hasToString(is(text))).inRoot(isPlatformPopup()).perform(click());
      }
    }
  }

  private static void openOverflow() {
    openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
  }

  private static void clickDisplayedView(@IdRes int id) {
    onView(displayedWithId(id)).perform(click());
  }

  private static void clickDisplayedView(String text) {
    onView(displayedWithText(text)).perform(click());
  }
}
