package com.schibsted.spain.barista;

import android.app.Activity;
import android.content.Intent;
import com.schibsted.spain.barista.cleardata.ClearDatabaseRule;
import com.schibsted.spain.barista.cleardata.ClearPreferencesRule;
import com.schibsted.spain.barista.flakyespresso.FlakyActivityTestRule;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class BaristaRule<T extends Activity> implements TestRule {

  private static final int DEFAULT_FLAKY_ATTEMPTS = 10;
  private static final boolean LAUNCH_ACTIVITY_AUTOMATICALLY = false;
  private static final boolean INITIAL_TOUCH_MODE_ENABLED = true;

  public static <T extends Activity> BaristaRule<T> create(Class<T> activityClass) {
    return new BaristaRule<>(activityClass);
  }

  private final ClearPreferencesRule clearPreferencesRule;
  private final ClearDatabaseRule clearDatabaseRule;
  private final FlakyActivityTestRule<T> activityTestRule;

  public BaristaRule(Class<T> activityClass) {
    this.clearDatabaseRule = new ClearDatabaseRule();
    this.clearPreferencesRule = new ClearPreferencesRule();
    this.activityTestRule = new FlakyActivityTestRule<>(activityClass,
        INITIAL_TOUCH_MODE_ENABLED,
        LAUNCH_ACTIVITY_AUTOMATICALLY)
        .allowFlakyAttemptsByDefault(DEFAULT_FLAKY_ATTEMPTS);
  }

  @Override
  public Statement apply(Statement base, Description description) {
    return RuleChain.outerRule(activityTestRule)
        .around(clearPreferencesRule)
        .around(clearDatabaseRule)
        .apply(base, description);
  }

  public void launchActivity() {
    activityTestRule.launchActivity(null);
  }

  public void launchActivity(Intent startIntent) {
    activityTestRule.launchActivity(startIntent);
  }

  public FlakyActivityTestRule<T> getActivityTestRule() {
    return activityTestRule;
  }
}
