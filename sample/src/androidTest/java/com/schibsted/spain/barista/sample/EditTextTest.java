package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.internal.util.KeyboardAction;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaHintAssertions.assertHint;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo;
import static com.schibsted.spain.barista.interaction.BaristaScrollInteractions.safelyScrollTo;

@RunWith(AndroidJUnit4.class)
public class EditTextTest {

  @Rule
  public ActivityTestRule<EditTextActivity> activityRule = new ActivityTestRule<>(EditTextActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkWriteOnEditText_whenEditTextIsVisible() {
    writeTo(R.id.edittext, "Hello!");
    assertDisplayed("Hello!");
  }

  @Test
  public void checkWriteOnEditText_whenScrollIsNeeded() {
    writeTo(R.id.edittext_very_far_away, "Hello!");
    safelyScrollTo(R.id.edittext_very_far_away);
    assertDisplayed("Hello!");
  }

  @Test
  public void checkWriteOnEditText_whenParentIsNotAScrollView() {
    writeTo(R.id.edittext_centered, "Hello!");
    assertDisplayed("Hello!");
  }

  @Test
  public void checkWriteAndCloseOnEditText_whenEditTextIsVisible() {
    writeTo(R.id.edittext, "Hello!", KeyboardAction.CLOSE);
    assertDisplayed("Hello!");
  }

  @Test
  public void checkWriteAndSubmitOnEditText_whenEditTextIsVisible() {
    writeTo(R.id.edittext, "Hello!", KeyboardAction.SUBMIT);
    assertDisplayed("Hello!");
    assertDisplayed("Submitted!");
  }

  @Test
  public void assertHintById() {
    assertHint(R.id.edittext_centered, R.string.centered_edittext);
  }

  @Test
  public void assertHintByString() {
    assertHint(R.id.edittext_centered, "I'm a centered edittext!");
  }
}
