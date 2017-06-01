package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.test.espresso.AmbiguousViewMatcherException;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;
import com.schibsted.spain.barista.androidresource.ResourceTypeChecker;
import com.schibsted.spain.barista.exception.BaristaArgumentTypeException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithText;

public class BaristaClickActions {

  private static final ResourceTypeChecker RESOURCE_TYPE_CHECKER = new ResourceTypeChecker();

  public static void click(int id) {
    if (isStringResource(id)) {
      clickByString(id);
    } else if (isIdResource(id)) {
      clickById(id);
    } else {
      throw new BaristaArgumentTypeException();
    }
  }

  private static void clickById(@IdRes int id) {
    try {
      scrollAndClickViewById(id);
    } catch (AmbiguousViewMatcherException multipleViewsMatched) {
      try {
        scrollAndClickDisplayedViewById(id);
      } catch (PerformException parentIsNotAnScrollView) {
        clickDisplayedViewById(id);
      }
    } catch (PerformException parentIsNotAnScrollView) {
      clickDisplayedViewById(id);
    }
  }

  private static void clickByString(@StringRes int id) {
    try {
      scrollAndClickViewByString(id);
    } catch (AmbiguousViewMatcherException multipleViewsMatched) {
      try {
        scrollAndClickDisplayedViewByString(id);
      } catch (PerformException parentIsNotAnScrollView) {
        clickDisplayedViewByString(id);
      }
    } catch (PerformException parentIsNotAnScrollView) {
      clickDisplayedViewByString(id);
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

  private static void scrollAndClickViewById(@IdRes int id) {
    onView(withId(id)).perform(scrollTo(), ViewActions.click());
  }

  private static void scrollAndClickViewByString(@StringRes int id) {
    onView(withText(id)).perform(scrollTo(), ViewActions.click());
  }

  private static void scrollAndClickView(String text) {
    onView(withText(text)).perform(scrollTo(), ViewActions.click());
  }

  private static void scrollAndClickDisplayedViewById(@IdRes int id) {
    onView(displayedWithId(id)).perform(scrollTo(), ViewActions.click());
  }
  private static void scrollAndClickDisplayedViewByString(@StringRes int id) {
    onView(displayedWithText(id)).perform(scrollTo(), ViewActions.click());
  }

  private static void scrollAndClickDisplayedView(String text) {
    onView(displayedWithText(text)).perform(scrollTo(), ViewActions.click());
  }

  private static void clickDisplayedViewById(@IdRes int id) {
    onView(displayedWithId(id)).perform(ViewActions.click());
  }

  private static void clickDisplayedViewByString(@StringRes int id) {
    onView(displayedWithText(id)).perform(ViewActions.click());
  }

  private static void clickDisplayedView(String text) {
    onView(displayedWithText(text)).perform(ViewActions.click());
  }

  private static boolean isIdResource(int id) {
    return RESOURCE_TYPE_CHECKER.isIdResource(id);
  }

  private static boolean isStringResource(int id) {
    return RESOURCE_TYPE_CHECKER.isStringResource(id);
  }
}
