package com.schibsted.spain.barista.flakyespresso;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class FlakyActivityTestRule<T extends Activity> extends ActivityTestRule<T> {
  public FlakyActivityTestRule(Class<T> activityClass) {
    super(activityClass);
  }

  public FlakyActivityTestRule(Class<T> activityClass, boolean initialTouchMode) {
    super(activityClass, initialTouchMode);
  }

  public FlakyActivityTestRule(Class<T> activityClass, boolean initialTouchMode, boolean launchActivity) {
    super(activityClass, initialTouchMode, launchActivity);
  }

  @Override
  public Statement apply(Statement base, Description description) {
    Statement activityStatement = super.apply(base, description);
    Statement repeatStatement = RepeatFlakyRule.createStatement(activityStatement, description);
    Statement flakyStatement = AllowFlakyRule.createStatement(repeatStatement, description);
    return flakyStatement;
  }
}
