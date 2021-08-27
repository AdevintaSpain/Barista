package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaHintAssertions.assertHint;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaEditTextInteractions.clearText;
import static com.adevinta.android.barista.interaction.BaristaEditTextInteractions.typeTo;
import static com.adevinta.android.barista.interaction.BaristaEditTextInteractions.writeTo;

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
    assertDisplayed("Hello!");
  }

  @Test
  public void checkWriteOnEditText_whenParentIsNotAScrollView() {
    writeTo(R.id.edittext_centered, "Hello!");
    assertDisplayed("Hello!");
  }

  @Test
  public void checkTypeToEditText_whenEditTextIsVisible() {
    typeTo(R.id.edittext_constrained, "Hello1=What's-Your*Name?");
    assertDisplayed("Hello1What");
  }

  @Test
  public void checkTypeToEditText_whenScrollIsNeeded() {
    typeTo(R.id.edittext_constrained_very_far_away, "Hello,ABC/123");
    assertDisplayed("HelloABC12");
  }

  @Test
  public void checkTypeToEditText_whenParentIsNotAScrollView() {
    typeTo(R.id.edittext_constrained_centered, " Hello 123 ");
    assertDisplayed("Hello123");
  }

  @Test
  public void assertHintById() {
    assertHint(R.id.edittext_centered, R.string.centered_edittext);
  }

  @Test
  public void assertHintByString() {
    assertHint(R.id.edittext_centered, "I'm a centered edittext!");
  }

  @Test
  public void checkTextCleared() {
    writeTo(R.id.edittext_centered, "Hello!");
    assertDisplayed("Hello!");

    clearText(R.id.edittext_centered);
    assertDisplayed("");
  }
}
