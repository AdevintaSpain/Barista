package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaErrorAssertions.assertError;
import static com.schibsted.spain.barista.assertion.BaristaHintAssertions.assertHint;

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
    assertError(R.id.hintanderror_inputlayout, "TextInputLayout error");
    assertError(R.id.hintanderror_inputedittext, "TextInputEditText error");
    assertError(R.id.hintanderror_edittext, "EditText error");
  }

  @Test
  public void assertErrorByResource() {
    assertError(R.id.hintanderror_inputlayout, R.string.hintanderror_inputlayout_error);
    assertError(R.id.hintanderror_inputedittext, R.string.hintanderror_inputedittext_error);
    assertError(R.id.hintanderror_edittext, R.string.hintanderror_edittext_error);
  }
}
