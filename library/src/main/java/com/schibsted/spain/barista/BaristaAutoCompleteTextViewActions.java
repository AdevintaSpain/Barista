package com.schibsted.spain.barista;

import android.support.annotation.IdRes;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.custom.AutocompleteViewActions.replaceAutocomplete;

public class BaristaAutoCompleteTextViewActions {

    public static void writeToAutocompleteEditText(@IdRes int id, String text) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            onView(withId(id)).perform(scrollTo(), replaceAutocomplete(text));
        } catch (Exception e) {
            onView(withId(id)).perform(replaceAutocomplete(text));
        }
    }

}
