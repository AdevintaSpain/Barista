package com.schibsted.spain.barista.rule.flaky.internal;

import android.util.Log;
import org.junit.runners.model.Statement;

import static com.schibsted.spain.barista.rule.flaky.internal.FlakyUtil.finishAllActivitiesOnUiThread;

public class AllowFlakyStatement extends Statement {

  private static final String TAG = "FLAKY";

  private final int attempts;
  private final Statement statement;

  public AllowFlakyStatement(int attempts, Statement statement) {
    this.attempts = attempts;
    this.statement = statement;
  }

  @Override
  @SuppressWarnings("PMD.AvoidCatchingThrowable")
  public void evaluate() throws Throwable {

    for (int i = 1; i <= attempts; i++) {
      try {
        Log.d(TAG, "--> Attempt #" + i);
        statement.evaluate();
        Log.d(TAG, "<-- Success at #" + i);
        break;
      } catch (Throwable e) {
        if (i == attempts) {
          Log.d(TAG, "<-- Attempt #" + i + " failed. No more attempts.");
          throw e;
        }
        Log.d(TAG, "<-- Attempt #" + i + " failed. Repeating again. The cause was:", e);

        // This is what JUnit and Espresso do after each test method:
        finishAllActivitiesOnUiThread();
      }
    }
  }
}
