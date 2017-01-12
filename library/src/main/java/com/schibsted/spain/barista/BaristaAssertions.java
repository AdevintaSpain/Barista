package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
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

  public static void assertDisplayed(int id) {
    try {
      onView(withId(id)).check(matches(isDisplayed()));
    } catch (Exception e) {
      onView(withText(id)).check(matches(isDisplayed()));
    }
  }

  public static void assertDisplayed(String text) {
    onView(withText(text)).check(matches(isDisplayed()));
  }

  public static void assertNotExist(int id) {
    try {
      onView(withId(id)).check(doesNotExist());
    } catch (Exception e) {
      onView(withText(id)).check(doesNotExist());
    }
  }

  public static void assertNotExist(String text) {
    onView(withText(text)).check(doesNotExist());
  }

  public static void assertNotDisplayed(int id) {
    try {
      onView(withId(id)).check(matches(not(isDisplayed())));
    } catch (Exception e) {
      onView(withText(id)).check(matches(not(isDisplayed())));
    }
  }

  public static void assertNotDisplayed(String text) {
    onView(withText(text)).check(matches(not(isDisplayed())));
  }

  public static void assertEnabled(int id) {
    try {
      onView(withId(id)).check(matches(isEnabled()));
    } catch (Exception e) {
      onView(withText(id)).check(matches(isEnabled()));
    }
  }

  public static void assertEnabled(String text) {
    onView(withText(text)).check(matches(isEnabled()));
  }

  public static void assertDisabled(int id) {
    try {
      onView(withId(id)).check(matches(not(isEnabled())));
    } catch (Exception e) {
      onView(withText(id)).check(matches(not(isEnabled())));
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
}