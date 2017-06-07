package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.AmbiguousViewMatcherException;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;
import android.view.View;
import com.schibsted.spain.barista.androidresource.ResourceTypeChecker;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.BaristaScrollToAction.scrollTo;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedAnd;

public class BaristaClickActions {

  private static final ResourceTypeChecker RESOURCE_TYPE_CHECKER = new ResourceTypeChecker();

  public static void click(@IdRes int id) {
    if (isIdResource(id)) {
      click(withId(id));
    } else if (isStringResource(id)) {
      click(withText(id));
    }
  }

  public static void click(String text) {
    click(withText(text));
  }

  public static void click(Matcher<View> viewMatcher) {
    try {
      scrollAndClickView(viewMatcher);
    } catch (AmbiguousViewMatcherException multipleViewsMatched) {
      try {
        scrollAndClickDisplayedView(viewMatcher);
      } catch (PerformException parentIsNotAnScrollView) {
        clickDisplayedView(viewMatcher);
      }
    } catch (PerformException parentIsNotAnScrollView) {
      clickDisplayedView(viewMatcher);
    }
  }

  public static void clickBack() {
    pressBack();
  }

  private static void scrollAndClickView(Matcher<View> viewMatcher) {
    onView(viewMatcher).perform(scrollTo(), ViewActions.click());
  }

  private static void scrollAndClickDisplayedView(Matcher<View> viewMatcher) {
    onView(displayedAnd(viewMatcher)).perform(scrollTo(), ViewActions.click());
  }

  private static void clickDisplayedView(Matcher<View> viewMatcher) {
    onView(displayedAnd(viewMatcher)).perform(ViewActions.click());
  }

  private static boolean isIdResource(int id) {
    return RESOURCE_TYPE_CHECKER.isIdResource(id);
  }

  private static boolean isStringResource(int id) {
    return RESOURCE_TYPE_CHECKER.isStringResource(id);
  }
}
