package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.test.espresso.NoActivityResumedException;
import android.util.Log;
import com.schibsted.spain.barista.androidresource.ResourceTypeChecker;
import com.schibsted.spain.barista.exception.BaristaArgumentTypeException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.custom.HelperMatchers.firstViewOf;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.fail;

public class BaristaAssertions {

  private static final ResourceTypeChecker RESOURCE_TYPE_CHECKER = new ResourceTypeChecker();

  public static void assertDisplayed(int id) {
    if (isIdResource(id)) {
      onView(withId(id)).check(matches(isDisplayed()));
    } else if (isStringResource(id)) {
      onView(withText(id)).check(matches(isDisplayed()));
    } else {
      throw new BaristaArgumentTypeException();
    }
  }

  public static void assertDisplayed(String text) {
    onView(firstViewOf(allOf(withText(text), isDisplayed()))).check(matches(isDisplayed()));
  }

  public static void assertNotExist(int id) {
    if (isIdResource(id)) {
      onView(withId(id)).check(doesNotExist());
    } else if (isStringResource(id)) {
      onView(withText(id)).check(doesNotExist());
    } else {
      throw new BaristaArgumentTypeException();
    }
  }

  public static void assertNotExist(String text) {
    onView(withText(text)).check(doesNotExist());
  }

  public static void assertNotDisplayed(int id) {
    if (isIdResource(id)) {
      onView(withId(id)).check(matches(not(isDisplayed())));
    } else if (isStringResource(id)) {
      onView(withText(id)).check(matches(not(isDisplayed())));
    } else {
      throw new BaristaArgumentTypeException();
    }
  }

  public static void assertNotDisplayed(String text) {
    onView(withText(text)).check(matches(not(isDisplayed())));
  }

  public static void assertEnabled(int id) {
    if (isIdResource(id)) {
      onView(withId(id)).check(matches(isEnabled()));
    } else if (isStringResource(id)) {
      onView(withText(id)).check(matches(isEnabled()));
    } else {
      throw new BaristaArgumentTypeException();
    }
  }

  public static void assertEnabled(String text) {
    onView(withText(text)).check(matches(isEnabled()));
  }

  public static void assertDisabled(int id) {
    if (isIdResource(id)) {
      onView(withId(id)).check(matches(not(isEnabled())));
    } else if (isStringResource(id)) {
      onView(withText(id)).check(matches(not(isEnabled())));
    } else {
      throw new BaristaArgumentTypeException();
    }
  }

  public static void assertDisabled(String text) {
    onView(withText(text)).check(matches(not(isEnabled())));
  }

  public static void assertThatBackButtonClosesTheApp() {
    try {
      pressBack(); // Will launch an Exception if it closes the app
      fail(); // One of our Activities is appearing on the screen :(
    } catch (NoActivityResumedException expectedException) {
      Log.d("Barista",
          "As the Activity is the first one of the stack, we expected this error. Yes, the back button closes the app!");
    }
  }

  public static void assertDrawerIsOpen(@IdRes int id) {
    onView(withId(id)).check(matches(isOpen()));
  }

  public static void assertDrawerIsClosed(@IdRes int id) {
    onView(withId(id)).check(matches(isClosed()));
  }

  public static void assertHint(@IdRes int id, @StringRes int text) {
    onView(withId(id)).check(matches(withHint(text)));
  }

  public static void assertHint(@IdRes int id, String text) {
    onView(withId(id)).check(matches(withHint(text)));
  }

  private static boolean isIdResource(int id) {
    return RESOURCE_TYPE_CHECKER.isIdResource(id);
  }

  private static boolean isStringResource(int id) {
    return RESOURCE_TYPE_CHECKER.isStringResource(id);
  }
}
