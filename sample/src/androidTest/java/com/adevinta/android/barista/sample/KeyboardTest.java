package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static com.adevinta.android.barista.interaction.BaristaKeyboardInteractions.pressImeActionButton;


public class KeyboardTest {

  @Rule
  public ActivityTestRule<KeyboardActivity> activityRule = new ActivityTestRule<>(KeyboardActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkPressedImeActionButton_whenIdProvided() {
    final String expectedText = "Edit text ime action button pressed!";
    assertNotExist(expectedText);

    pressImeActionButton(R.id.edittext);

    assertDisplayed(expectedText);
  }

  @Test
  public void checkPressedImeActionButton_whenIdProvidedAndNotFocused() {
    final String expectedText = "Edit text not focused ime action button pressed!";
    assertNotExist(expectedText);

    pressImeActionButton(R.id.edittext_not_focused);

    assertDisplayed(expectedText);
  }

  @Test
  public void checkPressedImeActionButton_whenIdProvidedAndScrollNeeded() {
    final String expectedText = "Edit text very far away ime action button pressed!";
    assertNotExist(expectedText);

    pressImeActionButton(R.id.edittext_very_far_away);

    assertDisplayed(expectedText);
  }

  @Test
  public void checkPressedImeActionButton_whenIdNotProvided() {
    final String expectedText = "Edit text ime action button pressed!";
    assertNotExist(expectedText);

    pressImeActionButton();

    assertDisplayed(expectedText);
  }
}
