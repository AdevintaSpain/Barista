package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.cleardata.MonitorRule;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaClickActions.click;
import static com.schibsted.spain.barista.BaristaDialogActions.clickDialogNegativeButton;
import static com.schibsted.spain.barista.BaristaDialogActions.clickDialogNeutralButton;
import static com.schibsted.spain.barista.BaristaDialogActions.clickDialogPositiveButton;

@RunWith(AndroidJUnit4.class)
public class DialogActivityTest {

  @Rule
  public MonitorRule monitorRule = new MonitorRule();

  @Rule
  public ActivityTestRule<DialogActivity> activityRule = new ActivityTestRule<>(DialogActivity.class);

  @Before
  public void setup() {
    click(R.id.button);
  }

  @Test
  @Ignore
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
