package com.schibsted.spain.barista.rule.flaky.internal;

import android.util.Log;
import org.junit.runners.model.Statement;

public class RepeatStatement extends Statement {

  private static final String TAG = "FLAKY";

  private final int times;
  private final Statement statement;

  public RepeatStatement(int times, Statement statement) {
    this.times = times;
    this.statement = statement;
  }

  @Override
  @SuppressWarnings("PMD.AvoidCatchingThrowable")
  public void evaluate() throws Throwable {
    for (int i = 0; i < times; i++) {
      try {
        Log.d(TAG, "--> Repetition #" + i);
        statement.evaluate();
        FlakyUtil.finishAllActivitiesOnUiThread();
        Log.d(TAG, "<-- Finished repetition #" + i + " successfully");
      } catch (Throwable throwable) {
        Log.e(TAG, "<-- Repetition #" + i + " failed!");
        throw throwable;
      }
    }
    Log.d(TAG, "All repetitions done without failure");
  }
}
