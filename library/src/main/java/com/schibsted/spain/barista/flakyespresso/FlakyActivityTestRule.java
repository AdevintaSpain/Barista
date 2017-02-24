package com.schibsted.spain.barista.flakyespresso;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class FlakyActivityTestRule<T extends Activity> extends ActivityTestRule<T> {

  private final FlakyActivityStatementBuilder statementBuilder = new FlakyActivityStatementBuilder();

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
  @SuppressWarnings("PMD.CloseResource")
  public Statement apply(Statement base, Description description) {
    Statement activityStatement = super.apply(base, description);
    return statementBuilder
        .withBase(activityStatement)
        .withDescription(description)
        .build();
  }
}
