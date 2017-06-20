package com.schibsted.spain.barista;

import com.schibsted.spain.barista.custom.SleepViewAction;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

public class BaristaSleepActions {

  public static void sleep(long millis) {
    onView(isRoot()).perform(SleepViewAction.sleep(millis));
  }

  public static void sleep(long units, TimeUnit timeunit) {
    sleep(timeunit.toMillis(units));
  }

  public static void sleepThread(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void sleepThread(long units, TimeUnit timeunit) {
    sleepThread(timeunit.toMillis(units));
  }
}
