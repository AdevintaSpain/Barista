package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.longClickOn;

@RunWith(AndroidJUnit4.class)
public class LongClickIdAndTextTest {

  @Rule
  public ActivityTestRule<ClickIdTextActivity> activityRule = new ActivityTestRule<>(ClickIdTextActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkLongClick_byIdAndText() {
    longClickOn(R.id.button, "Button 1");
    assertDisplayed("Button 1 long clicked!");
    assertNotExist("Button 2 long clicked!");
  }

  @Test
  public void checkOtherLongClick_byIdAndText() {
    longClickOn(R.id.button, "Button 2");
    assertDisplayed("Button 2 long clicked!");
    assertNotExist("Button 1 long clicked!");
  }
}
