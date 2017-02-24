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
  @SuppressWarnings("PMD.CloseResource")
  public Statement apply(Statement base, Description description) {
    Statement activityStatement = super.apply(base, description);
    return createAllowOrRepeatStatement(activityStatement, description);
  }

  static Statement createAllowOrRepeatStatement(Statement base, Description description) {
    Repeat repeat = description.getAnnotation(Repeat.class);
    AllowFlaky allowFlaky = description.getAnnotation(AllowFlaky.class);
    boolean hasRepeatAnnotation = repeat != null;
    boolean hasAllowFlakyAnnotation = allowFlaky != null;

    if (hasAllowFlakyAnnotation && hasRepeatAnnotation) {
      throw new IllegalStateException("Both @Repeat and @AllowFlaky annotations are not allowed on "
          + "the same test method. Found both at "
          + description.getDisplayName());
    } else if (hasRepeatAnnotation) {
      int times = repeat.times();
      return new RepeatStatement(times, base);
    } else if (hasAllowFlakyAnnotation) {
      int attempts = allowFlaky.attempts();
      return new AllowFlakyStatement(attempts, base);
    } else {
      return base;
    }
  }
}
