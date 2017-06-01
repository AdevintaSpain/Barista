package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import com.schibsted.spain.barista.BaristaClickActions;
import com.schibsted.spain.barista.cleardata.ClearFilesRule;
import org.junit.Rule;
import org.junit.Test;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;

public class ClearFilesRuleTest {

  @Rule
  public ActivityTestRule<FileActivity> activityRule =
      new ActivityTestRule<>(FileActivity.class, true, false);

  @Rule
  public ClearFilesRule clearFilesRule = new ClearFilesRule();

  //
  // Only one of these two tests will succeed when files are not cleared.
  // They rely on files being removed between test executions.
  //

  @Test
  public void checkOnceThatValueIsZeroFistAndOneAfterIncrement() throws Exception {
    activityRule.launchActivity(null);

    assertCurrentValueIs("0");
    incrementValue();
    assertCurrentValueIs("1");
  }

  @Test
  public void checkTwiceThatValueIsZeroFistAndOneAfterIncrement() throws Exception {
    activityRule.launchActivity(null);

    assertCurrentValueIs("0");
    incrementValue();
    assertCurrentValueIs("1");
  }

  private void assertCurrentValueIs(String expectedValue) {
    assertDisplayed(expectedValue);
  }

  private void incrementValue() {
    BaristaClickActions.click(R.id.file_increment_button);
  }
}