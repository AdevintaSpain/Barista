package com.schibsted.spain.barista;

import android.support.annotation.IdRes;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.BaristaScrollActions.scrollTo;

public class BaristaEditTextActions {

    public static void writeToEditText(@IdRes int id, String text) {
        scrollTo(id);
        onView(withId(id)).perform(replaceText(text));
    }
}
