package com.schibsted.spain.barista.cleardata;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * This rule logs test name on start and end. It's useful to maintain the logs alive.
 * <p>
 * There are some C.I. tools, like Travis, that kills processes that doesn't log
 * nothing on the stdout. So, using this Rule, Travis will love your build until
 * the Espresso tests ends.
 */
public class MonitorRule implements TestRule {

  @Override
  public Statement apply(final Statement base, final Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        System.out.println(description.getDisplayName());
        System.out.println("Test started!");
        long startTime = System.currentTimeMillis();
        base.evaluate();
        long processTime = System.currentTimeMillis() - startTime;
        System.out.println("Test ended! It took " + processTime + "ms");
      }
    };
  }
}