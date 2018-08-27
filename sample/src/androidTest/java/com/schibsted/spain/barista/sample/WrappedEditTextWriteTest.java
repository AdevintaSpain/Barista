package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaAutoCompleteTextViewInteractions.writeToAutoComplete;
import static com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo;

@RunWith(AndroidJUnit4.class)
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
