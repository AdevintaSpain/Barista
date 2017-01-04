package com.schibsted.spain.barista;

import android.support.annotation.IdRes;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class BaristaSwipeActions {

    public static void swipePagerForward(@IdRes int id) {
        onView(withId(id)).perform(swipeLeft());
    }

    public static void swipePagerBack(@IdRes int id) {
        onView(withId(id)).perform(swipeRight());
    }

}
