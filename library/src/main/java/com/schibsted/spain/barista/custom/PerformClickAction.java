package com.schibsted.spain.barista.custom;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.view.View;

import org.hamcrest.Matcher;

public class PerformClickAction {

  /**
   * Common Espresso's ViewActions.click() taps on the center of the View.
   * But, if that View has a children placed on its center, that child will
   * be clicked instead of the View itself.
   *
   * This Action fixes that behavior, just clicking on the View using its
   * instance, not his position.
   *
   * @see ViewActions.click()
   */
  public static ViewAction clickUsingPerformClick() {
    return new ViewAction() {
      @Override
      public Matcher<View> getConstraints() {
        return null;
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