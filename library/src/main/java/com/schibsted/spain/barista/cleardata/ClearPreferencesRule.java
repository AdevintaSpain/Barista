package com.schibsted.spain.barista.cleardata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * This rule clears all app's SharedPreferences before running each test
 */
public class ClearPreferencesRule implements TestRule {

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
    List<SharedPreferences> allPrefs = getAllPreferencesFiles();
    for (SharedPreferences prefs : allPrefs) {
      prefs.edit().clear().apply();
    }
  }

  @NonNull
  private List<SharedPreferences> getAllPreferencesFiles() {
    Context context = InstrumentationRegistry.getTargetContext().getApplicationContext();
    String rootPath = context.getApplicationInfo().dataDir + "/shared_prefs";
    File prefsFolder = new File(rootPath);

    String[] children = prefsFolder.list();
    if (children == null) {
      return Collections.emptyList();
    }

    List<SharedPreferences> allPrefs = new ArrayList<>();
    for (String prefFileName : children) {
      String prefName = prefFileName.endsWith(".xml")
          ? prefFileName.substring(0, prefFileName.indexOf(".xml"))
          : prefFileName;
      SharedPreferences prefs = context.getSharedPreferences(prefName, 0);
      allPrefs.add(prefs);
    }
    return allPrefs;
  }
}