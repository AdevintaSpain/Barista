package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaSeekBarInteractions.setProgressTo;
import static com.schibsted.spain.barista.interaction.BaristaSeekBarInteractions.setProgressToMax;
import static com.schibsted.spain.barista.interaction.BaristaSeekBarInteractions.setProgressToMin;

@RunWith(AndroidJUnit4.class)
public class SeekBarTest {

  @Rule
  public ActivityTestRule<SeekBarActivity> activityRule = new ActivityTestRule<>(SeekBarActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkSetProgress() {
    setProgressTo(R.id.seek_bar, 16);
    assertDisplayed("16");
  }

  @Test
  public void checkSetProgressToMax() {
    setProgressToMax(R.id.seek_bar);
    assertDisplayed("100");
  }

  @Test
  public void checkSetProgressToMin() {
    setProgressToMin(R.id.seek_bar);
    assertDisplayed("0");
  }
}
