package com.schibsted.spain.barista.flakyespresso;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * This is a subclass of {@link ActivityTestRule} that enables repetition of flaky tests using the annotations {@link AllowFlaky} and {@link
 * Repeat}. Check out the docs on each one to see how they behave.
 */
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

  public FlakyActivityTestRule<T> allowFlakyAttemptsByDefault(int defaultAttempts) {
    statementBuilder.allowFlakyAttemptsByDefault(defaultAttempts);
    return this;
  }

  @Override
  @SuppressWarnings("PMD.CloseResource")
  public Statement apply(Statement base, Description description) {
    Statement activityStatement = super.apply(base, description);
    return statementBuilder
        .setBase(activityStatement)
        .setDescription(description)
        .build();
  }
}
