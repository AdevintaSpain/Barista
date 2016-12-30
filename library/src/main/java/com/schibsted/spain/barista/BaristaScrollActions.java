package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.action.ViewActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class BaristaScrollActions {

    public static void scrollTo(@IdRes int id) {
        onView(withId(id)).perform(ViewActions.scrollTo());
    }

    public static void scrollTo(String text) {
        onView(withText(text)).perform(ViewActions.scrollTo());
    }

}
