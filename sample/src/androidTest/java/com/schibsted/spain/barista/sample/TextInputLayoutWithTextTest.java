package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo;

@RunWith(AndroidJUnit4.class)
public class TextInputLayoutWithTextTest {

  @Rule
  public ActivityTestRule<HintAndErrorActivity> activityRule = new ActivityTestRule<>(HintAndErrorActivity.class);

  @Test
  public void assertText() {
    writeTo(R.id.hintanderror_inputlayout_editText, "Test text");
    assertDisplayed(R.id.hintanderror_inputlayout, "Test text");
  }
}
