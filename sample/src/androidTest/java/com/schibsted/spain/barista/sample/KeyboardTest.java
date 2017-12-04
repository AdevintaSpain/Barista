package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static com.schibsted.spain.barista.interaction.BaristaKeyboardInteractions.pressImeActionButton;

@RunWith(AndroidJUnit4.class)
public class KeyboardTest {

  @Rule
  public ActivityTestRule<KeyboardActivity> activityRule = new ActivityTestRule<>(KeyboardActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkPressedImeActionButton_whenIsVisible() {
    final String buttonPressedText = "Ime action button pressed!";
    assertNotExist(buttonPressedText);

    pressImeActionButton(R.id.edittext);
    assertDisplayed(buttonPressedText);
  }
}
