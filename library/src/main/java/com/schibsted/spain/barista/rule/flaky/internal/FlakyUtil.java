package com.schibsted.spain.barista.rule.flaky.internal;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.util.Log;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class FlakyUtil {

  public static final String TAG = "FLAKY";

  /**
   * Closes all current activities.
   */
  public static void finishAllActivitiesOnUiThread() {
    InstrumentationRegistry.getInstrumentation()
        .runOnMainSync(new Runnable() {
          @Override
          public void run() {
            finishAllActivities();
          }
        });
  }

  /**
   * Copied from {@link android.support.test.runner.MonitoringInstrumentation.ActivityFinisher}
   */
  private static void finishAllActivities() {
    List<Activity> activities = new ArrayList<>();

    for (Stage s : EnumSet.range(Stage.CREATED, Stage.STOPPED)) {
      activities.addAll(ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(s));
    }

    Log.i(TAG, "Activities that are still in CREATED to STOPPED: " + activities.size());

    for (Activity activity : activities) {
      if (!activity.isFinishing()) {
        try {
          Log.i(TAG, "Finishing activity: " + activity);
          activity.finish();
        } catch (RuntimeException e) {
          Log.e(TAG, "Failed to finish activity.", e);
        }
      }
    }
  }
}
