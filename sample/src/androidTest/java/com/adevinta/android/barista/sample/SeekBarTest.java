package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaRatingBarInteractions.setRatingTo;
import static com.adevinta.android.barista.interaction.BaristaRatingBarInteractions.setRatingToMax;
import static com.adevinta.android.barista.interaction.BaristaRatingBarInteractions.setRatingToMin;
import static com.adevinta.android.barista.interaction.BaristaSeekBarInteractions.setProgressTo;
import static com.adevinta.android.barista.interaction.BaristaSeekBarInteractions.setProgressToMax;
import static com.adevinta.android.barista.interaction.BaristaSeekBarInteractions.setProgressToMin;

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
