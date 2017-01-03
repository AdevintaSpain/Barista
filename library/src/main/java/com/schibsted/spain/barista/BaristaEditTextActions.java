package com.schibsted.spain.barista;

import android.support.annotation.IdRes;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.custom.AutocompleteViewActions.replaceAutocomplete;

public class BaristaEditTextActions {

    public static void writeToEditText(@IdRes int id, String text) {
        try {
            onView(withId(id)).perform(scrollTo(), replaceText(text));
        } catch (Exception e) {
            onView(withId(id)).perform(replaceText(text));
        }
    }

    public static void writeToAutocompleteEditText(@IdRes int id, String text) {
        try {
            onView(withId(id)).perform(scrollTo(), replaceAutocomplete(text));
        } catch (Exception e) {
            onView(withId(id)).perform(replaceAutocomplete(text));
        }
    }

}
