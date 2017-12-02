package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaSeekBarInteractions.swipeLeft;
import static com.schibsted.spain.barista.interaction.BaristaSeekBarInteractions.swipeRight;

@RunWith(AndroidJUnit4.class)
public class SeekBarTest {

  @Rule
  public ActivityTestRule<SeekBarActivity> activityRule = new ActivityTestRule<>(SeekBarActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkSwipeSeekBarRight() {
    swipeRight(R.id.seek_bar);
    assertDisplayed("2");
  }

  @Test
  public void checkSwipeSeekBarLeft() {
    swipeLeft(R.id.seek_bar);
    assertDisplayed("0");
  }
}
