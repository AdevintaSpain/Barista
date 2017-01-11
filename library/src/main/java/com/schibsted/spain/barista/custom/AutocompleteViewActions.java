package com.schibsted.spain.barista.custom;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ReplaceTextAction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import javax.annotation.Nonnull;
import org.hamcrest.Matcher;

import static android.support.test.espresso.action.ViewActions.actionWithAssertions;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;

public class AutocompleteViewActions {

  public static ViewAction replaceAutocomplete(@Nonnull String stringToBeSet) {
    return actionWithAssertions(new ReplaceAutocompleteTextAction(stringToBeSet));
  }

  /**
   * This is based on {@link ReplaceTextAction}
   * with modifications thanks to "http://www.grokkingandroid.com/how-androids-autocompletetextview-nearly-drove-me-nuts/".
   * <p>
   * It replaces the text without showing the autocomplete popup.
   * Bear in mind it won't work if you use a custom adapter not extending {@link ArrayAdapter}.
   * <p>
   */
  public static class ReplaceAutocompleteTextAction implements ViewAction {
    private final String stringToBeSet;

    public ReplaceAutocompleteTextAction(String value) {
      checkNotNull(value);
      this.stringToBeSet = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Matcher<View> getConstraints() {
      return allOf(isDisplayed(), isAssignableFrom(AutoCompleteTextView.class));
    }

    @Override
    public void perform(UiController uiController, View view) {
      AutoCompleteTextView autocomplete = (AutoCompleteTextView) view;

      ArrayAdapter adapter = (ArrayAdapter) autocomplete.getAdapter();
      autocomplete.setAdapter(null);

      ((EditText) view).setText(stringToBeSet);

      autocomplete.setAdapter(adapter);
    }

    @Override
    public String getDescription() {
      return "replace text without showing autocomplete suggestions";
    }
  }
}
