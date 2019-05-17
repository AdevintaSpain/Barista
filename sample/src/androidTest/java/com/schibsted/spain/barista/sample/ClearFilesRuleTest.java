package com.schibsted.spain.barista.sample;

import android.content.Intent;
import androidx.test.rule.ActivityTestRule;
import com.schibsted.spain.barista.rule.cleardata.ClearFilesRule;
import org.junit.Rule;
import org.junit.Test;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;

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
  public void checkOnceThatValueIsZeroFistAndOneAfterIncrement_whenSavingAtPath() throws Exception {
    launchActivitySavingFileAtPath("/");

    assertCurrentValueIs("0");
    incrementValue();
    assertCurrentValueIs("1");
  }

  @Test
  public void checkTwiceThatValueIsZeroFistAndOneAfterIncrement_whenSavingAtPath() throws Exception {
    launchActivitySavingFileAtPath("/");

    assertCurrentValueIs("0");
    incrementValue();
    assertCurrentValueIs("1");
  }

  @Test
  public void checkOnceThatValueIsZeroFistAndOneAfterIncrement_whenSavingAtSubdirectory() throws Exception {
    launchActivitySavingFileAtPath("/subdirectory/");

    assertCurrentValueIs("0");
    incrementValue();
    assertCurrentValueIs("1");
  }

  @Test
  public void checkTwiceThatValueIsZeroFistAndOneAfterIncrement_whenSavingAtSubdirectory() throws Exception {
    launchActivitySavingFileAtPath("/subdirectory/");

    assertCurrentValueIs("0");
    incrementValue();
    assertCurrentValueIs("1");
  }

  private void launchActivitySavingFileAtPath(String path) {
    Intent i = new Intent();
    i.putExtra(FileActivity.EXTRA_PATH, path);
    activityRule.launchActivity(i);
  }

  private void assertCurrentValueIs(String expectedValue) {
    assertDisplayed(expectedValue);
  }

  private void incrementValue() {
    clickOn(R.id.file_increment_button);
  }
}