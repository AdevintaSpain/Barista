package com.adevinta.android.barista.internal.matcher;

import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import org.hamcrest.Matcher;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

public class DisplayedMatchers {

  @NonNull
  public static Matcher<View> displayedWithId(@IdRes int id) {
    return allOf(isDisplayed(), withId(id));
  }

  @NonNull
  public static Matcher<View> displayedWithIdAndClass(@IdRes int id, final Class<? extends View> viewClass) {
    return allOf(isDisplayed(), withId(id), isAssignableFrom(viewClass));
  }

  @NonNull
  public static Matcher<View> displayedAssignableFrom(final Class<? extends View> viewClass) {
    return allOf(isDisplayed(), isAssignableFrom(viewClass));
  }

  @NonNull
  public static Matcher<View> displayedAnd(Matcher<View> anotherMatcher) {
    return allOf(isDisplayed(), anotherMatcher);
  }
}
