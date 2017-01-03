package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.action.ViewActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class BaristaClickActions {

    public static void click(@IdRes int id) {
        try {
            onView(withId(id)).perform(scrollTo(), ViewActions.click());
        } catch (Exception e) {
            onView(withId(id)).perform(ViewActions.click());
        }
    }

    public static void click(String text) {
        try {
            onView(withText(text)).perform(scrollTo(), ViewActions.click());
        } catch (Exception e) {
            onView(withText(text)).perform(ViewActions.click());
        }
    }

    public static void clickBack() {
        pressBack();
    }
}
