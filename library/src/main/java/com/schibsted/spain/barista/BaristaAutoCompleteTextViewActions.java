package com.schibsted.spain.barista;

import android.support.annotation.IdRes;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.BaristaScrollActions.scrollTo;
import static com.schibsted.spain.barista.custom.AutocompleteViewActions.replaceAutocomplete;

public class BaristaAutoCompleteTextViewActions {

    public static void writeToAutocompleteEditText(@IdRes int id, String text) {
        scrollTo(id);
        onView(withId(id)).perform(replaceAutocomplete(text));
    }
}
