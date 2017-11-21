package com.schibsted.spain.barista;

import android.support.test.espresso.PerformException;
import android.view.View;
import com.schibsted.spain.barista.androidresource.ResourceTypeChecker;
import com.schibsted.spain.barista.custom.NestedEnabledScrollToAction;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Scrolls in Espresso are not as great as we could except. For that reason, we will try
 * to scroll several times instead of just one. If any of them worked, we will share the
 * Espresso's Exception to the caller.
 */
public class BaristaScrollActions {

  private static final ResourceTypeChecker RESOURCE_TYPE_CHECKER = new ResourceTypeChecker();

  private static final int MAX_SCROLL_ATTEMPTS = 100;

  public static void scrollTo(int id) {
    if (isIdResource(id)) {
      performWithMatcher(withId(id));
    } else if (isStringResource(id)) {
      performWithMatcher(withText(id));
    }
  }

  public static void scrollTo(String text) {
    performWithMatcher(withText(text));
  }

  private static void performWithMatcher(Matcher<View> viewMatcher) {
    for (int i = 0; i <= MAX_SCROLL_ATTEMPTS; i++) {
      try {
        onView(viewMatcher).perform(NestedEnabledScrollToAction.scrollTo());
      } catch (PerformException exception) {
        if (i == MAX_SCROLL_ATTEMPTS) {
          throw exception;
        }
      }
    }
  }

  private static boolean isIdResource(int id) {
    return RESOURCE_TYPE_CHECKER.isIdResource(id);
  }

  private static boolean isStringResource(int id) {
    return RESOURCE_TYPE_CHECKER.isStringResource(id);
  }
}
