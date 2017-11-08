package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import com.schibsted.spain.barista.BaristaClickActions;
import com.schibsted.spain.barista.cleardata.ClearPreferencesRule;
import org.junit.Rule;
import org.junit.Test;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;

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
    BaristaClickActions.clickOn(R.id.preference_increment_button);
  }
}