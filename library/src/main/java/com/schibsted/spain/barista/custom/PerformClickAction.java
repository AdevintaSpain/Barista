package com.schibsted.spain.barista.custom;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

public class PerformClickAction {

  /**
   * Common Espresso's ViewActions.click() taps on the center of the View.
   * But, if that View has a children placed on its center, that child will
   * be clicked instead of the View itself.
   *
   * This Action fixes that behavior, just clicking on the View using its
   * instance, not its position.
   *
   * @see ViewActions.click()
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
        view.performClick();
      }
    };
  }
}