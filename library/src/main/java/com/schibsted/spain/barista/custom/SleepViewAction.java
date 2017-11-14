package com.schibsted.spain.barista.custom;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

public class SleepViewAction {

  public static ViewAction sleep(final long millis) {
    return new ViewAction() {
      @Override
      public Matcher<View> getConstraints() {
        return isRoot();
      }

      @Override
      public String getDescription() {
        return "Wait for at least " + millis + " millis";
      }

      @Override
      public void perform(final UiController uiController, final View view) {
        uiController.loopMainThreadUntilIdle();
        uiController.loopMainThreadForAtLeast(millis);
      }
    };
  }
}
