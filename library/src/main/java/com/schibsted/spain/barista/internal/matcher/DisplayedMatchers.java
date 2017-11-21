package com.schibsted.spain.barista.internal.matcher;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

public class DisplayedMatchers {

  @NonNull
  public static Matcher<View> displayedWithId(@IdRes int id) {
    return allOf(isDisplayed(), withId(id));
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
