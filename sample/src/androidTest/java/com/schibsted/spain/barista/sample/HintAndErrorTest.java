package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.internal.failurehandler.BaristaException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaErrorAssertions.assertError;
import static com.schibsted.spain.barista.assertion.BaristaErrorAssertions.assertNoError;
import static com.schibsted.spain.barista.assertion.BaristaHintAssertions.assertHint;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;

@RunWith(AndroidJUnit4.class)
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
    assertError(R.id.hintanderror_inputlayout, "TextInputLayout error");
    assertError(R.id.hintanderror_inputedittext, "TextInputEditText error");
    assertError(R.id.hintanderror_edittext, "EditText error");
  }

  @Test
  public void assertErrorByResource() {
    clickOn(R.id.showErrors);
    assertError(R.id.hintanderror_inputlayout, R.string.hintanderror_inputlayout_error);
    assertError(R.id.hintanderror_inputedittext, R.string.hintanderror_inputedittext_error);
    assertError(R.id.hintanderror_edittext, R.string.hintanderror_edittext_error);
  }

  @Test(expected = BaristaException.class)
  public void assertErrorByStringFails() {
    assertError(R.id.hintanderror_inputlayout, "TextInputLayout error");
    assertError(R.id.hintanderror_inputedittext, "TextInputEditText error");
    assertError(R.id.hintanderror_edittext, "EditText error");
  }

  @Test(expected = BaristaException.class)
  public void assertErrorByResourceFails() {
    assertError(R.id.hintanderror_inputlayout, R.string.hintanderror_inputlayout_error);
    assertError(R.id.hintanderror_inputedittext, R.string.hintanderror_inputedittext_error);
    assertError(R.id.hintanderror_edittext, R.string.hintanderror_edittext_error);
  }

  @Test
  public void assertNoErrorByString() {
    assertNoError(R.id.hintanderror_inputlayout, "TextInputLayout NoError");
    assertNoError(R.id.hintanderror_inputedittext, "TextInputEditText NoError");
    assertNoError(R.id.hintanderror_edittext, "EditText NoError");
  }

  @Test
  public void assertNoErrorByResource() {
    assertNoError(R.id.hintanderror_inputlayout, R.string.hintanderror_inputlayout_error);
    assertNoError(R.id.hintanderror_inputedittext, R.string.hintanderror_inputedittext_error);
    assertNoError(R.id.hintanderror_edittext, R.string.hintanderror_edittext_error);
  }

  @Test(expected = BaristaException.class)
  public void assertNoErrorByResourceFails() {
    clickOn(R.id.showErrors);
    assertNoError(R.id.hintanderror_inputlayout, R.string.hintanderror_inputlayout_error);
    assertNoError(R.id.hintanderror_inputedittext, R.string.hintanderror_inputedittext_error);
    assertNoError(R.id.hintanderror_edittext, R.string.hintanderror_edittext_error);
  }
}
