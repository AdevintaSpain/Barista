package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.rule.cleardata.ClearPreferencesRule;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn;

public class ClearPreferencesRuleTest {

  @Rule
  public ActivityTestRule<PreferencesActivity> activityRule = new ActivityTestRule<>(PreferencesActivity.class, true, false);

  @Rule
  public ClearPreferencesRule clearPreferencesRule = new ClearPreferencesRule();

  //
  // Only one of these two tests will succeed when preferences are not cleared.
  // They rely on preferences being cleared between test executions.
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
    clickOn(R.id.preference_increment_button);
  }
}