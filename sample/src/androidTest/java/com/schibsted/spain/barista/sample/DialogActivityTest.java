package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.action.BaristaClickActions.clickOn;
import static com.schibsted.spain.barista.action.BaristaDialogActions.clickDialogNegativeButton;
import static com.schibsted.spain.barista.action.BaristaDialogActions.clickDialogNeutralButton;
import static com.schibsted.spain.barista.action.BaristaDialogActions.clickDialogPositiveButton;

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
