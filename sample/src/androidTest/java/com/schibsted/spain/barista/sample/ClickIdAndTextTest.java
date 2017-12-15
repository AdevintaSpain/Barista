package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;

@RunWith(AndroidJUnit4.class)
public class ClickIdAndTextTest {

  @Rule
  public ActivityTestRule<ClickIdTextActivity> activityRule = new ActivityTestRule<>(ClickIdTextActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkClick_byIdAndText() {
    clickOn(R.id.button, "Button 1");
    assertDisplayed("Button 1 clicked!");
    assertNotExist("Button 2 clicked!");
  }

  @Test
  public void checkOtherClick_byIdAndText() {
    clickOn(R.id.button, "Button 2");
    assertDisplayed("Button 2 clicked!");
    assertNotExist("Button 1 clicked!");
  }
}
