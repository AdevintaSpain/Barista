package com.adevinta.android.barista.rule.flaky.internal;

import com.adevinta.android.barista.rule.flaky.AllowFlaky;
import com.adevinta.android.barista.rule.flaky.Repeat;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class FlakyStatementBuilder {

  private Statement base;
  private Description description;
  private boolean useAllowFlakyByDefault = false;
  private int defaultAllowFlakyAttempts = 0;
  private int defaultRepeatAttempts = 1;
  private boolean useRepeatByDefault = false;

  public FlakyStatementBuilder setBase(Statement base) {
    this.base = base;
    return this;
  }

  public FlakyStatementBuilder setDescription(Description description) {
    this.description = description;
    return this;
  }

  public FlakyStatementBuilder setRepeatAttemptsByDefault(int attempts) {
    useAllowFlakyByDefault = false;
    useRepeatByDefault = true;
    defaultRepeatAttempts = attempts;
    return this;
  }

  public FlakyStatementBuilder allowFlakyAttemptsByDefault(int attempts) {
    useAllowFlakyByDefault = true;
    useRepeatByDefault = false;
    defaultAllowFlakyAttempts = attempts;
    return this;
  }

  public Statement build() {
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
    } else if (useAllowFlakyByDefault) {
      return new AllowFlakyStatement(defaultAllowFlakyAttempts, base);
    } else if (useRepeatByDefault) {
      return new RepeatStatement(defaultRepeatAttempts, base);
    } else {
      return base;
    }
  }
}
