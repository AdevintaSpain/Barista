package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaRatingBarInteractions.setRatingTo;
import static com.schibsted.spain.barista.interaction.BaristaRatingBarInteractions.setRatingToMax;
import static com.schibsted.spain.barista.interaction.BaristaRatingBarInteractions.setRatingToMin;
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
  public void checkSeekBarSetProgress() {
    setProgressTo(R.id.seek_bar, 16);
    assertDisplayed("16");
  }

  @Test
  public void checkSeekBarSetProgressToMax() {
    setProgressToMax(R.id.seek_bar);
    assertDisplayed("100");
  }

  @Test
  public void checkSeekBarSetProgressToMin() {
    setProgressToMin(R.id.seek_bar);
    assertDisplayed("0");
  }

  @Test
  public void checkRatingBarSetProgress() {
    setRatingTo(R.id.rating_bar, 3);
    assertDisplayed("3.0");
  }

  @Test
  public void checkRatingBarSetProgressToMax() {
    setRatingToMax(R.id.rating_bar);
    assertDisplayed("7.0");
  }

  @Test
  public void checkRatingBarSetProgressToMin() {
    setRatingTo(R.id.rating_bar, 3);
    setRatingToMin(R.id.rating_bar);
    assertDisplayed("0.0");
  }
}
