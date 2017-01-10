package com.schibsted.spain.barista;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class BaristaDialogActions {

    public static void clickDialogPositiveButton() {
        onView(withId(android.R.id.button1)).perform(click());
    }

    public static void clickDialogNegativeButton() {
        onView(withId(android.R.id.button2)).perform(click());
    }

    public static void clickDialogNeutralButton() {
        onView(withId(android.R.id.button3)).perform(click());
    }
}
