package com.schibsted.spain.barista.action;

import android.support.test.espresso.contrib.PickerActions;
import android.widget.DatePicker;

import org.hamcrest.Matchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedWithId;

public class BaristaPickerActions {

  public static void setDateOnPicker(int year, int month, int day) {
    onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(
        PickerActions.setDate(year, month, day));
    onView(displayedWithId(android.R.id.button1)).perform(click());
  }
}
