package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaSeekBarAssertions.assertProgress;
import static com.schibsted.spain.barista.assertion.BaristaSeekBarAssertions.assertProgressIsMax;
import static com.schibsted.spain.barista.assertion.BaristaSeekBarAssertions.assertProgressIsMin;

@RunWith(AndroidJUnit4.class)
public class SeekBarAssertionsTest {

  @Rule
  public ActivityTestRule<SomeSeekBarsWithDifferentProgressesActivity> activityRule =
      new ActivityTestRule<>(SomeSeekBarsWithDifferentProgressesActivity.class);

  @Test
  public void checkProgress() {
    assertProgress(R.id.seek_bar_progress_27, 27);
  }

  @Test
  public void checkMinProgress() {
    assertProgressIsMin(R.id.seek_bar_min_progress);
  }

  @Test
  public void checkMaxProgress() {
    assertProgressIsMax(R.id.seek_bar_max_progress);
  }
}
