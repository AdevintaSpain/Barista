package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.schibsted.spain.barista.rule.cleardata.ClearDatabaseRule;
import org.junit.Rule;
import org.junit.Test;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;

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
    clickOn(R.id.database_increment_button);
  }
}