package com.schibsted.spain.barista.internal.viewaction;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import android.view.View;
import org.hamcrest.Matcher;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

public class PerformClickAction {

  /**
   * Common Espresso's ViewActions.click() taps on the center of the View.
   * But, if that View has a children placed on its center, that child will
   * be clicked instead of the View itself.
   *
   * This Action fixes that behavior, just clicking on the View using its
   * instance, not its position.
   */
  public static ViewAction clickUsingPerformClick() {
    return new ViewAction() {
      @Override
      public Matcher<View> getConstraints() {
        return isDisplayed();
      }

      @Override
      public String getDescription() {
        return "Click on the view using performClick()";
      }

      @Override
      public void perform(UiController uiController, View view) {
        if (view.isClickable()) {
          view.performClick();
        } else {
          propagateClickToChildren(uiController, view);
        }
      }

      private void propagateClickToChildren(UiController uiController, View view) {
        click().perform(uiController, view);
      }
    };
  }
}