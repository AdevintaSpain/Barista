package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.PerformException;
import android.util.Log;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static com.schibsted.spain.barista.BaristaScrollActions.scrollTo;
import static com.schibsted.spain.barista.custom.DisplayedMatchers.displayedWithId;

public class BaristaEditTextActions {

  public static void writeToEditText(@IdRes int id, String text) {
    try {
      scrollTo(id);
    } catch (PerformException exception) {
      Log.d("Barista",
          "The View's parent is not a ScrollView. Due to the power of Barista, you can ignore this error message");
    } finally {
      onView(displayedWithId(id)).perform(replaceText(text));
    }
  }
}
