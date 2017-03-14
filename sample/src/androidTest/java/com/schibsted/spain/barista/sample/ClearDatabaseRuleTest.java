package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import com.schibsted.spain.barista.BaristaClickActions;
import com.schibsted.spain.barista.cleardata.ClearDatabaseRule;
import com.schibsted.spain.barista.cleardata.ClearPreferencesRule;
import org.junit.Rule;
import org.junit.Test;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;

public class ClearDatabaseRuleTest {

  @Rule
  public ActivityTestRule<DatabaseActivity> activityRule = new ActivityTestRule<>(DatabaseActivity.class, true, false);

  @Rule
  public ClearDatabaseRule clearDatabaseRule = new ClearDatabaseRule();

  //
  // Only one of these two tests will succeed when database is not cleared.
  // They rely on database being cleared between test executions.
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
    BaristaClickActions.click(R.id.database_increment_button);
  }
}