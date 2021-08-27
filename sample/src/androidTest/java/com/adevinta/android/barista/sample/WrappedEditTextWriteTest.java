package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaAutoCompleteTextViewInteractions.writeToAutoComplete;
import static com.adevinta.android.barista.interaction.BaristaEditTextInteractions.writeTo;

public class WrappedEditTextWriteTest {

  @Rule
  public ActivityTestRule<WrappedEditTextActivity> activityRule = new ActivityTestRule<>(WrappedEditTextActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkWriteTo_onSearchView() {
    writeTo(R.id.searchview, "Apple");
    assertDisplayed(R.id.searchResult, "Apple");
  }

  @Test
  public void checkWriteTo_onSupportSearchView() {
    writeTo(R.id.supportSearchView, "Banana");
    assertDisplayed(R.id.searchResult, "Banana");
  }

  @Test
  public void checkWriteTo_onTextInputLayout() {
    writeTo(R.id.textInput, "Kiwi");
    assertDisplayed(R.id.searchResult, "Kiwi");
  }

  @Test
  public void checkWriteOnAutoComplete_onSearchView() {
    writeToAutoComplete(R.id.searchview, "Apple");
    assertDisplayed(R.id.searchResult, "Apple");
  }

  @Test
  public void checkWriteOnAutoComplete_onSupportSearchView() {
    writeToAutoComplete(R.id.supportSearchView, "Banana");
    assertDisplayed(R.id.searchResult, "Banana");
  }
}
