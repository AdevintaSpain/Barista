package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;
import static com.schibsted.spain.barista.interaction.BaristaDialogInteractions.clickDialogNegativeButton;
import static com.schibsted.spain.barista.interaction.BaristaDialogInteractions.clickDialogNeutralButton;
import static com.schibsted.spain.barista.interaction.BaristaDialogInteractions.clickDialogPositiveButton;

@RunWith(AndroidJUnit4.class)
public class DialogActivityTest {

  @Rule
  public ActivityTestRule<DialogActivity> activityRule = new ActivityTestRule<>(DialogActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Before
  public void setup() {
    clickOn(R.id.button);
  }

  @Test
  public void positiveButton() {
    clickDialogPositiveButton();
    assertDisplayed("positive");
  }

  @Test
  public void negativeButton() {
    clickDialogNegativeButton();
    assertDisplayed("negative");
  }

  @Test
  public void neutralButton() {
    clickDialogNeutralButton();
    assertDisplayed("neutral");
  }
}
