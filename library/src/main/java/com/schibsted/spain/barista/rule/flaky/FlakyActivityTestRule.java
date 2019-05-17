package com.schibsted.spain.barista.rule.flaky;

import android.app.Activity;
import androidx.test.rule.ActivityTestRule;
import com.schibsted.spain.barista.rule.BaristaRule;
import com.schibsted.spain.barista.rule.flaky.internal.FlakyStatementBuilder;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * This is a subclass of {@link ActivityTestRule} that enables repetition of flaky tests using the annotations {@link AllowFlaky} and {@link
 * Repeat}. Check out the docs on each one to see how they behave.
 *
 * @deprecated Use a {@link FlakyTestRule} in combination with {@link ActivityTestRule}, or use {@link BaristaRule}.
 */
@Deprecated
public class FlakyActivityTestRule<T extends Activity> extends ActivityTestRule<T> {

  private final FlakyStatementBuilder statementBuilder = new FlakyStatementBuilder();

  public FlakyActivityTestRule(Class<T> activityClass) {
    super(activityClass);
  }

  public FlakyActivityTestRule(Class<T> activityClass, boolean initialTouchMode) {
    super(activityClass, initialTouchMode);
  }

  public FlakyActivityTestRule(Class<T> activityClass, boolean initialTouchMode, boolean launchActivity) {
    super(activityClass, initialTouchMode, launchActivity);
  }

  /**
   * Utility method to use {@link AllowFlaky} by default in all test methods.
   * <br>
   * Use this method when constructing the Rule to apply a default behavior of {@link AllowFlaky} without having to add the annotation to
   * each test. Use it wisely when your tests are very flaky, something quite common with Espresso.
   * <br>
   * The behavior will be overridden with {@link Repeat} or {@link AllowFlaky}.
   */
  public FlakyActivityTestRule<T> allowFlakyByDefault(int defaultAttempts) {
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
