package com.schibsted.spain.barista.custom;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

public class ClickRootAction {

  public static ViewAction clickRoot() {
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