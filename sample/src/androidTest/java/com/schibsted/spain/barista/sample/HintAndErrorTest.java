package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaErrorAssertions.assertError;
import static com.schibsted.spain.barista.assertion.BaristaHintAssertions.assertHint;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;

@RunWith(AndroidJUnit4.class)
public class HintAndErrorTest {

  @Rule
  public ActivityTestRule<HintAndErrorActivity> activityRule = new ActivityTestRule<>(HintAndErrorActivity.class);

  @Test
  public void assertLayoutHintById() {
    assertHint(R.id.hintanderror_inputlayout1, R.string.hintanderror_layout_hint);
  }

  @Test
  public void assertLayoutHintByString() {
    assertHint(R.id.hintanderror_inputlayout1, "hint and error layout - layout hint");
  }

  @Test
  public void assertInputEditTextHintById() {
    assertHint(R.id.hintanderror_inputEditText2, R.string.hintanderror_text_input_layout_hint);
  }

  @Test
  public void assertInputEditTextHintByString() {
    assertHint(R.id.hintanderror_inputEditText2, "Hint and error layout - edit text hint");
  }

  @Test
  public void assertEditTextHintById() {
    assertHint(R.id.hintanderror_edittext, R.string.hintanderror_edittext_hint);
  }

  @Test
  public void assertEditTextHintByString() {
    assertHint(R.id.hintanderror_edittext, "Hint and error - edit text hint");
  }

  @Test
  public void assertLayoutErrorById() {
    clickOn(R.id.hintanderror_showLayoutErrorButton);
    assertError(R.id.hintanderror_inputlayout1, R.string.hintanderror_layout_error);
  }

  @Test
  public void assertLayoutErrorByString() {
    clickOn(R.id.hintanderror_showLayoutErrorButton);
    assertError(R.id.hintanderror_inputlayout1, "Hint and error - layout error");
  }

  @Test
  public void assertInputEditTextErrorById() {
    clickOn(R.id.hintanderror_showInputEditTextError);
    assertError(R.id.hintanderror_inputEditText1, R.string.hintanderror_text_input_layout_error);
  }

  @Test
  public void assertInputEditTextErrorByString() {
    clickOn(R.id.hintanderror_showInputEditTextError);
    assertError(R.id.hintanderror_inputEditText1, "Hint and error - text input error");
  }

  @Test
  public void assertEditTextErrorById() {
    clickOn(R.id.hintanderror_showEditTextError);
    assertError(R.id.hintanderror_edittext, R.string.hintanderror_edittext_error);
  }

  @Test
  public void assertEditTextErrorByString() {
    clickOn(R.id.hintanderror_showEditTextError);
    assertError(R.id.hintanderror_edittext, "Hint and error - edit text error");
  }
}
