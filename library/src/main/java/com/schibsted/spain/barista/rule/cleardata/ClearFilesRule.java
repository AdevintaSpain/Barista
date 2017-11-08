package com.schibsted.spain.barista.rule.cleardata;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import com.schibsted.spain.barista.rule.cleardata.internal.FileOperations;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * This rule clears all app's stored files before running each test
 */
public class ClearFilesRule implements TestRule {

  @Override
  public Statement apply(final Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        clearData();
        base.evaluate();
        clearData();
      }
    };
  }

  private void clearData() {
    FileOperations.deleteAllFiles(getAppContext());
  }

  private Context getAppContext() {
    return InstrumentationRegistry.getTargetContext();
  }
}