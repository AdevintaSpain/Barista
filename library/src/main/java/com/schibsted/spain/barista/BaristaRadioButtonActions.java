package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.action.ViewActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class BaristaRadioButtonActions {

  public static void clickRadioButtonItem(@IdRes int radioGroupId, int itemToClickId) {
    onView(allOf(withParent(withId(radioGroupId)), withId(itemToClickId))).perform(ViewActions.click());
  }

  public static void clickRadioButtonItem(@IdRes int radioGroupId, String text) {
    onView(allOf(withParent(withId(radioGroupId)), withText(text))).perform(ViewActions.click());
  }
}
