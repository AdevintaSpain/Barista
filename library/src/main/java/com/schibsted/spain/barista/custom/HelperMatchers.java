package com.schibsted.spain.barista.custom;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class HelperMatchers {

  public static <T> Matcher<T> atPosition(final int position, final Matcher<T> matcher) {
    return new BaseMatcher<T>() {
      int matchingPosition = 0;

      @Override
      public boolean matches(final Object item) {
        boolean result = false;

        if (matcher.matches(item)) {
          if (matchingPosition == position) {
            result = true;
          }
          matchingPosition++;
        }

        return result;
      }

      @Override
      public void describeTo(final Description description) {
        description.appendText("should return matching item at position " + position);
      }
    };
  }
}
