package com.schibsted.spain.barista;

import android.support.annotation.IdRes;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.custom.HelperMatchers.atPosition;
import static org.hamcrest.Matchers.allOf;

public class BaristaRadioButtonActions {

  public static void clickRadioButtonItem(@IdRes int radioGroupId, @IdRes int itemToClickId) {
    onView(allOf(withParent(withId(radioGroupId)), withId(itemToClickId))).perform(click());
  }

  public static void clickRadioButtonItem(@IdRes int radioGroupId, String text) {
    onView(allOf(withParent(withId(radioGroupId)), withText(text))).perform(click());
  }

  public static void clickRadioButtonPosition(@IdRes int radioGroupId, int position) {
    onView(atPosition(position, withParent(withId(radioGroupId)))).perform(click());
  }

}
