package com.schibsted.spain.barista.internal.viewaction;

import android.support.annotation.IdRes;
import android.support.test.espresso.ViewAction;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.internal.viewaction.ChildActions.performOnChildWithId;
import static com.schibsted.spain.barista.internal.viewaction.ChildActions.performOnChildWithMatcher;

public class ClickChildAction {

  public static ViewAction clickChildWithId(@IdRes final int childId) {
    return performOnChildWithId(childId, click());
  }

  public static ViewAction clickChildWithText(final String text) {
    return performOnChildWithMatcher(withText(text), click());
  }
}
