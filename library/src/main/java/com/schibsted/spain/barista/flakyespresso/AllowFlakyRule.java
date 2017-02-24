package com.schibsted.spain.barista.flakyespresso;

import android.util.Log;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static com.schibsted.spain.barista.flakyespresso.FlakyUtil.finishAllActivitiesOnUiThread;

public class AllowFlakyRule implements TestRule {

  private static final int DEFAULT_FLAKY_ATTEMPTS = 5;

  @Override
  public Statement apply(Statement base, Description description) {
    return createStatement(base, description);
  }

  public static Statement createStatement(Statement base, Description description) {
    int attempts = DEFAULT_FLAKY_ATTEMPTS;

    AllowFlaky repeat = description.getAnnotation(AllowFlaky.class);
    if (repeat != null) {
      attempts = repeat.attempts();
    }
    return new AllowFlakyStatement(attempts, base);
  }

  private static class AllowFlakyStatement extends Statement {

    private final int attempts;
    private final Statement statement;

    private AllowFlakyStatement(int attempts, Statement statement) {
      this.attempts = attempts;
      this.statement = statement;
    }

    @Override
    public void evaluate() throws Throwable {

      for (int i = 1; i <= attempts; i++) {
        try {
          Log.d("FLAKY", "--> Attempt #" + i);
          statement.evaluate();
          Log.d("FLAKY", "<-- Success at #" + i);
          break;
        } catch (Throwable e) {
          if (i == attempts) {
            Log.d("FLAKY", "<-- Attempt #" + i + " failed. No more attempts.");
            throw e;
          }
          Log.d("FLAKY", "<-- Attempt #" + i + " failed. Repeating again");

          // This is what JUnit and Espresso do after each test method:
          finishAllActivitiesOnUiThread();
        }
      }
    }
  }
}
