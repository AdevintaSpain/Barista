package com.schibsted.spain.barista.interaction;

import com.schibsted.spain.barista.internal.viewaction.SleepViewAction;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

public class BaristaSleepInteractions {

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
      throw new IllegalStateException(e);
    }
  }

  public static void sleepThread(long units, TimeUnit timeunit) {
    sleepThread(timeunit.toMillis(units));
  }
}
