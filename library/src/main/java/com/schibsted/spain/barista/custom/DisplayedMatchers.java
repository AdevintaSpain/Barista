package com.schibsted.spain.barista.custom;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class DisplayedMatchers {

  @NonNull
  public static Matcher<View> displayedWithId(@IdRes int id) {
    return allOf(isDisplayed(), withId(id));
  }

  @NonNull
  public static Matcher<View> displayedWithText(@StringRes int text) {
    return allOf(isDisplayed(), withText(text));
  }

  @NonNull
  public static Matcher<View> displayedWithText(String text) {
    return allOf(isDisplayed(), withText(text));
  }
}
