package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.widget.TextView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedAssignableFrom;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithDescription;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithText;
import static org.hamcrest.core.CombinableMatcher.either;

public class BaristaMenuClickActions {

  public static void menuClick(@IdRes int id) {
    try {
      clickDisplayedView(id);
    } catch (NoMatchingViewException noMatchingViewException) {
      openOverflow();
      onView(either(displayedAssignableFrom(TextView.class)).or(displayedWithId(id))).perform(click());
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
        onView(displayedWithText(text)).perform(click());
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
