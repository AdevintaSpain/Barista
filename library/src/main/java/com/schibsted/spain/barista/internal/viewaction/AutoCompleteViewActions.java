package com.schibsted.spain.barista.internal.viewaction;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ReplaceTextAction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import javax.annotation.Nonnull;
import org.hamcrest.Matcher;

import static androidx.test.espresso.action.ViewActions.actionWithAssertions;
import static androidx.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;

public class AutoCompleteViewActions {

  public static ViewAction replaceAutoComplete(@Nonnull String stringToBeSet) {
    return actionWithAssertions(new ReplaceAutoCompleteTextAction(stringToBeSet));
  }

  /**
   * This is based on {@link ReplaceTextAction}
   * with modifications thanks to "http://www.grokkingandroid.com/how-androids-autocompletetextview-nearly-drove-me-nuts/".
   * <p>
   * It replaces the text without showing the autocomplete popup.
   * Bear in mind it won't work if you use a custom adapter not extending {@link ArrayAdapter}.
   * <p>
   */
  public static class ReplaceAutoCompleteTextAction implements ViewAction {
    private final String stringToBeSet;

    public ReplaceAutoCompleteTextAction(String value) {
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
      AutoCompleteTextView autoComplete = (AutoCompleteTextView) view;

      ListAdapter adapter = autoComplete.getAdapter();
      autoComplete.setAdapter(null);

      ((EditText) view).setText(stringToBeSet);

      if (adapter instanceof ArrayAdapter) {
        autoComplete.setAdapter((ArrayAdapter) adapter);
      } else if (adapter instanceof CursorAdapter) {
        autoComplete.setAdapter((CursorAdapter) adapter);
      } else if (adapter instanceof androidx.cursoradapter.widget.CursorAdapter) {
        autoComplete.setAdapter((androidx.cursoradapter.widget.CursorAdapter) adapter);
      }
    }

    @Override
    public String getDescription() {
      return "replace text without showing autocomplete suggestions";
    }
  }
}
