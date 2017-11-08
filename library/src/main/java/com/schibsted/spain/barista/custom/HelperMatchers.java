package com.schibsted.spain.barista.custom;

import android.support.annotation.IdRes;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.MenuItem;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class HelperMatchers {

  public static <T> Matcher<T> atPosition(final int position, final Matcher<T> matcher) {
    return new BaseMatcher<T>() {
      int matchingPosition = 0;

      @Override
      public boolean matches(final Object item) {
        if (!matcher.matches(item)) {
          return false;
        }

        if (matchingPosition++ == position) {
          return true;
        }
        return false;
      }

      @Override
      public void describeTo(final Description description) {
        description.appendText("should return matching item at position " + position);
      }
    };
  }

  public static <T> Matcher<T> firstViewOf(final Matcher<T> matcher) {
    return new BaseMatcher<T>() {
      private boolean isFirst = true;

      @Override
      public boolean matches(final Object item) {
        if (isFirst && matcher.matches(item)) {
          isFirst = false;
          return true;
        }
        return false;
      }

      @Override
      public void describeTo(final Description description) {
        description.appendText("should return first matching item");
      }
    };
  }

  public static Matcher<MenuItem> menuIdMatcher(final @IdRes int id) {
    return new BoundedMatcher<MenuItem, MenuItem>(MenuItem.class) {

      @Override
      protected boolean matchesSafely(MenuItem item) {
        return item.getItemId() == id;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("should return menu item with id " + id);
      }
    };
  }
}
