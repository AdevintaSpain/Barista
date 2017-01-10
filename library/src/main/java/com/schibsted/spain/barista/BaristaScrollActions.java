package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.action.ViewActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Scrolls in Espresso are not as great as we could except. For that reason, we will try
 * to scroll several times instead of just one. If any of them worked, we will share the
 * Espresso's Exception to the caller.
 */
public class BaristaScrollActions {

    private static final int MAX_SCROLL_ATTEMPTS = 100;

    public static void scrollTo(@IdRes int id) {
        for (int i = 0; i <= MAX_SCROLL_ATTEMPTS; i++) {
            try {
                onView(withId(id)).perform(ViewActions.scrollTo());
            } catch (PerformException exception) {
                if (i == MAX_SCROLL_ATTEMPTS) {
                    throw exception;
                }
            }
        }
    }

    public static void scrollTo(String text) {
        for (int i = 0; i <= MAX_SCROLL_ATTEMPTS; i++) {
            try {
                onView(withText(text)).perform(ViewActions.scrollTo());
            } catch (PerformException exception) {
                if (i == MAX_SCROLL_ATTEMPTS) {
                    throw exception;
                }
            }
        }
    }

}
