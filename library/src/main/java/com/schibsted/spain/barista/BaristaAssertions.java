package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.test.espresso.NoActivityResumedException;
import android.util.Log;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.fail;

public class BaristaAssertions {

  public static void assertViewIsDisplayed(@IdRes int id) {
    onView(withId(id)).check(matches(isDisplayed()));
  }

  public static void assertTextIsDisplayed(@StringRes int text) {
    onView(withText(text)).check(matches(isDisplayed()));
  }

  public static void assertTextIsDisplayed(String text) {
    onView(withText(text)).check(matches(isDisplayed()));
  }

  public static void assertViewDoesNotExist(@IdRes int viewId) {
    onView(withId(viewId)).check(doesNotExist());
  }

  public static void assertTextDoesNotExist(@StringRes int text) {
    onView(withText(text)).check(doesNotExist());
  }

  public static void assertTextDoesNotExist(String text) {
    onView(withText(text)).check(doesNotExist());
  }

  public static void assertViewIsNotDisplayed(@IdRes int id) {
    onView(withId(id)).check(matches(not(isDisplayed())));
  }

  public static void assertTextIsNotDisplayed(@StringRes int text) {
    onView(withText(text)).check(matches(not(isDisplayed())));
  }

  public static void assertTextIsNotDisplayed(String text) {
    onView(withText(text)).check(matches(not(isDisplayed())));
  }

  public static void assertViewIsEnabled(@IdRes int viewId) {
    onView(withId(viewId)).check(matches(isEnabled()));
  }

  public static void assertTextIsEnabled(@StringRes int text) {
    onView(withText(text)).check(matches(isEnabled()));
  }

  public static void assertTextIsEnabled(String text) {
    onView(withText(text)).check(matches(isEnabled()));
  }

  public static void assertViewIsDisabled(@IdRes int viewId) {
    onView(withId(viewId)).check(matches(not(isEnabled())));
  }

  public static void assertTextIsDisabled(@StringRes int text) {
    onView(withText(text)).check(matches(not(isEnabled())));
  }

  public static void assertTextIsDisabled(String text) {
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
}