package com.schibsted.spain.barista.flakyespresso;

import android.util.Log;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static com.schibsted.spain.barista.flakyespresso.FlakyUtil.finishAllActivitiesOnUiThread;

public class RepeatFlakyRule implements TestRule {

  private static final String TAG = "FLAKY";

  @Override
  public Statement apply(Statement base, Description description) {
    return createStatement(base, description);
  }

  public static Statement createStatement(Statement base, Description description) {
    Statement result = base;
    Repeat repeat = description.getAnnotation(Repeat.class);
    if (repeat != null) {
      int times = repeat.times();
      result = new RepeatStatement(times, base);
    }
    return result;
  }

  private static class RepeatStatement extends Statement {

    private final int times;
    private final Statement statement;

    private RepeatStatement(int times, Statement statement) {
      this.times = times;
      this.statement = statement;
    }

    @Override
    public void evaluate() throws Throwable {
      for (int i = 0; i < times; i++) {
        try {
          Log.d(TAG, "--> Repetition #" + i);
          statement.evaluate();
          finishAllActivitiesOnUiThread();
          Log.d(TAG, "<-- Finished repetition #" + i + " successfully");
        } catch (Throwable throwable) {
          Log.e(TAG, "<-- Repetition #" + i + " failed!");
          throw throwable;
        }
      }
      Log.d(TAG, "All repetitions done without failure");
    }
  }
}
