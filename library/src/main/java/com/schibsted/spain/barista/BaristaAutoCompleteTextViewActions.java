package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.PerformException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.BaristaScrollActions.scrollTo;
import static com.schibsted.spain.barista.custom.AutocompleteViewActions.replaceAutocomplete;

public class BaristaAutoCompleteTextViewActions {

    public static void writeToAutoCompleteTextView(@IdRes int id, String text) {
        try {
            scrollTo(id);
        } catch (PerformException exception) {
            // We expect this exception if the parent is not a ScrollView
        } finally {
            onView(withId(id)).perform(replaceAutocomplete(text));
        }
    }
}
