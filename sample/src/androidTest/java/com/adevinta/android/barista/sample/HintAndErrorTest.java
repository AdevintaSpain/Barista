package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.internal.failurehandler.BaristaException;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaErrorAssertions.assertErrorDisplayed;
import static com.adevinta.android.barista.assertion.BaristaErrorAssertions.assertNoErrorDisplayed;
import static com.adevinta.android.barista.assertion.BaristaHintAssertions.assertHint;
import static com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn;

public class HintAndErrorTest {

  @Rule
  public ActivityTestRule<HintAndErrorActivity> activityRule = new ActivityTestRule<>(HintAndErrorActivity.class);

  @Test
  public void assertHintByString() {
    assertHint(R.id.hintanderror_inputlayout, "TextInputLayout hint");
    assertHint(R.id.hintanderror_inputedittext, "TextInputEditText hint");
    assertHint(R.id.hintanderror_edittext, "EditText hint");
  }

  @Test
  public void assertHintByResource() {
    assertHint(R.id.hintanderror_inputlayout, R.string.hintanderror_inputlayout_hint);
    assertHint(R.id.hintanderror_inputedittext, R.string.hintanderror_inputedittext_hint);
    assertHint(R.id.hintanderror_edittext, R.string.hintanderror_edittext_hint);
  }

  @Test
  public void assertErrorByString() {
    clickOn(R.id.showErrors);
    assertErrorDisplayed(R.id.hintanderror_inputlayout, "TextInputLayout error");
    assertErrorDisplayed(R.id.hintanderror_inputlayout, "TextInputLayout error");
    assertErrorDisplayed(R.id.hintanderror_inputedittext, "TextInputEditText error");
    assertErrorDisplayed(R.id.hintanderror_edittext, "EditText error");
  }

  @Test
  public void assertErrorByResource() {
    clickOn(R.id.showErrors);
    assertErrorDisplayed(R.id.hintanderror_inputlayout, R.string.hintanderror_inputlayout_error);
    assertErrorDisplayed(R.id.hintanderror_inputedittext, R.string.hintanderror_inputedittext_error);
    assertErrorDisplayed(R.id.hintanderror_edittext, R.string.hintanderror_edittext_error);
  }

  @Test(expected = BaristaException.class)
  public void assertErrorByStringFails() {
    assertErrorDisplayed(R.id.hintanderror_inputlayout, "TextInputLayout error");
    assertErrorDisplayed(R.id.hintanderror_inputedittext, "TextInputEditText error");
    assertErrorDisplayed(R.id.hintanderror_edittext, "EditText error");
  }

  @Test(expected = BaristaException.class)
  public void assertErrorByResourceFails() {
    assertErrorDisplayed(R.id.hintanderror_inputlayout, R.string.hintanderror_inputlayout_error);
    assertErrorDisplayed(R.id.hintanderror_inputedittext, R.string.hintanderror_inputedittext_error);
    assertErrorDisplayed(R.id.hintanderror_edittext, R.string.hintanderror_edittext_error);
  }

  @Test
  public void assertNoErrorByResource() {
    assertNoErrorDisplayed(R.id.hintanderror_inputlayout);
    assertNoErrorDisplayed(R.id.hintanderror_inputedittext);
    assertNoErrorDisplayed(R.id.hintanderror_edittext);
  }

  @Test(expected = BaristaException.class)
  public void assertNoErrorByResourceFails() {
    clickOn(R.id.showErrors);
    assertNoErrorDisplayed(R.id.hintanderror_inputlayout);
    assertNoErrorDisplayed(R.id.hintanderror_inputedittext);
    assertNoErrorDisplayed(R.id.hintanderror_edittext);
  }
}
