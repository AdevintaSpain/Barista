package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.AmbiguousViewMatcherException;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithText;

public class BaristaClickActions {

  public static void click(@IdRes int id) {
    try {
      onView(withId(id)).perform(scrollTo(), ViewActions.click());
    } catch (AmbiguousViewMatcherException multipleViewsMatched) {
      try {
        onView(displayedWithId(id)).perform(scrollTo(), ViewActions.click());
      } catch (PerformException parentIsNotAnScrollView) {
        onView(displayedWithId(id)).perform(ViewActions.click());
      }
    } catch (PerformException parentIsNotAnScrollView) {
      onView(displayedWithId(id)).perform(ViewActions.click());
    }
  }

  public static void click(String text) {
    try {
      onView(withText(text)).perform(scrollTo(), ViewActions.click());
    } catch (AmbiguousViewMatcherException multipleViewsMatched) {
      try {
        onView(displayedWithText(text)).perform(scrollTo(), ViewActions.click());
      } catch (PerformException parentIsNotAnScrollView) {
        onView(displayedWithText(text)).perform(ViewActions.click());
      }
    } catch (PerformException parentIsNotAnScrollView) {
      onView(displayedWithText(text)).perform(ViewActions.click());
    }
  }

  public static void clickBack() {
    pressBack();
  }
}
