package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import junit.framework.AssertionFailedError;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaProgressBarAssertions.assertProgress;
import static com.adevinta.android.barista.assertion.BaristaProgressBarAssertions.assertProgressIsMax;
import static com.adevinta.android.barista.assertion.BaristaProgressBarAssertions.assertProgressIsMin;

public class ProgressBarMatchersTest {

  @Rule public ActivityTestRule<SomeSeekBarsWithDifferentProgressesActivity> activityRule =
      new ActivityTestRule<>(SomeSeekBarsWithDifferentProgressesActivity.class);

  @Test
  public void checkProgress() {
    assertProgress(R.id.seek_bar_progress_27, 27);
  }

  @Test(expected = AssertionFailedError.class)
  public void checkProgress_failsWhenDifferent() {
    assertProgress(R.id.seek_bar_progress_27, 10);
  }

  @Test
  public void checkMinProgress() {
    assertProgressIsMin(R.id.seek_bar_min_progress);
  }

  @Test(expected = AssertionFailedError.class)
  public void checkMinProgress_failsWhenProgressNotMin() {
    assertProgressIsMin(R.id.seek_bar_max_progress);
  }

  @Test
  public void checkMaxProgress() {
    assertProgressIsMax(R.id.seek_bar_max_progress);
  }

  @Test(expected = AssertionFailedError.class)
  public void checkMaxProgress_failsWhenProgressNotMax() {
    assertProgressIsMax(R.id.seek_bar_min_progress);
  }
}
