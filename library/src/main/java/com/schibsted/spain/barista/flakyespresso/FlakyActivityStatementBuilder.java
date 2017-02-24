package com.schibsted.spain.barista.flakyespresso;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class FlakyActivityStatementBuilder {

  private Statement base;
  private Description description;

  public FlakyActivityStatementBuilder withBase(Statement base) {
    this.base = base;
    return this;
  }

  public FlakyActivityStatementBuilder withDescription(Description description) {
    this.description = description;
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
    } else {
      return base;
    }
  }
}
