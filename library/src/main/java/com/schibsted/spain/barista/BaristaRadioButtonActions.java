package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.PerformException;
import android.util.Log;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static com.schibsted.spain.barista.BaristaScrollActions.scrollTo;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithText;
import static com.schibsted.spain.barista.custom.HelperMatchers.atPosition;
import static org.hamcrest.Matchers.allOf;

public class BaristaRadioButtonActions {

  public static void clickRadioButtonItem(@IdRes int radioGroupId, @IdRes int itemToClickId) {
    try {
      scrollTo(radioGroupId);
    } catch (PerformException exception) {
      Log.d("Barista",
          "The View's parent is not a ScrollView. Due to the power of Barista, you can ignore this error message");
    } finally {
      onView(
          allOf(withParent(displayedWithId(radioGroupId)), displayedWithId(itemToClickId))).perform(
          click());
    }
  }

  public static void clickRadioButtonItem(@IdRes int radioGroupId, String text) {
    try {
      scrollTo(radioGroupId);
    } catch (PerformException exception) {
      Log.d("Barista",
          "The View's parent is not a ScrollView. Due to the power of Barista, you can ignore this error message");
    } finally {
      onView(allOf(withParent(displayedWithId(radioGroupId)), displayedWithText(text))).perform(
          click());
    }
  }

  public static void clickRadioButtonPosition(@IdRes int radioGroupId, int position) {
    try {
      scrollTo(radioGroupId);
    } catch (PerformException exception) {
      Log.d("Barista",
          "The View's parent is not a ScrollView. Due to the power of Barista, you can ignore this error message");
    } finally {
      onView(atPosition(position, withParent(displayedWithId(radioGroupId)))).perform(click());
    }
  }
}
