package com.schibsted.spain.barista;

import android.support.test.espresso.AmbiguousViewMatcherException;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.action.ViewActions;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;
import com.schibsted.spain.barista.androidresource.ResourceTypeChecker;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedAnd;
import static com.schibsted.spain.barista.custom.NestedEnabledScrollToAction.scrollTo;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.AnyOf.anyOf;

public class BaristaClickActions {

  private static final ResourceTypeChecker RESOURCE_TYPE_CHECKER = new ResourceTypeChecker();

  public static void click(int id) {
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
      clickDisplayedView(viewMatcher);
    } catch (NoMatchingViewException noMatchingError) {
      try {
        scrollAndClickView(viewMatcher);
      } catch (AmbiguousViewMatcherException multipleViewsError) {
        scrollAndClickDisplayedView(viewMatcher);
      }
    }
  }

  public static void clickBack() {
    pressBack();
  }

  private static void scrollAndClickView(Matcher<View> viewMatcher) {
    onView(viewMatcher).perform(scrollTo(), ViewActions.click());
  }

  private static void scrollAndClickDisplayedView(Matcher<View> viewMatcher) {
    onView(allOf(
        viewMatcher,
        isDescendantOfA(allOf(
            isDisplayed(),
            anyOf(
                isAssignableFrom(ScrollView.class),
                isAssignableFrom(HorizontalScrollView.class),
                isAssignableFrom(ListView.class),
                isAssignableFrom(NestedScrollView.class)
            )
        ))
    )).perform(scrollTo(), ViewActions.click());
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
