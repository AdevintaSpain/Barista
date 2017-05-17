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
      scrollAndClickView(id);
    } catch (AmbiguousViewMatcherException multipleViewsMatched) {
      try {
        scrollAndClickDisplayedView(id);
      } catch (PerformException parentIsNotAnScrollView) {
        clickDisplayedView(id);
      }
    } catch (PerformException parentIsNotAnScrollView) {
      clickDisplayedView(id);
    }
  }

  public static void click(String text) {
    try {
      scrollAndClickView(text);
    } catch (AmbiguousViewMatcherException multipleViewsMatched) {
      try {
        scrollAndClickDisplayedView(text);
      } catch (PerformException parentIsNotAnScrollView) {
        clickDisplayedView(text);
      }
    } catch (PerformException parentIsNotAnScrollView) {
      clickDisplayedView(text);
    }
  }

  public static void clickBack() {
    pressBack();
  }

  private static void scrollAndClickView(@IdRes int id) {
    onView(withId(id)).perform(scrollTo(), ViewActions.click());
  }

  private static void scrollAndClickView(String text) {
    onView(withText(text)).perform(scrollTo(), ViewActions.click());
  }

  private static void scrollAndClickDisplayedView(@IdRes int id) {
    onView(displayedWithId(id)).perform(scrollTo(), ViewActions.click());
  }

  private static void scrollAndClickDisplayedView(String text) {
    onView(displayedWithText(text)).perform(scrollTo(), ViewActions.click());
  }

  private static void clickDisplayedView(@IdRes int id) {
    onView(displayedWithId(id)).perform(ViewActions.click());
  }

  private static void clickDisplayedView(String text) {
    onView(displayedWithText(text)).perform(ViewActions.click());
  }
}
