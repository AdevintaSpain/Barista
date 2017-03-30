package com.schibsted.spain.barista.custom;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

public class ClickChildAction {

  public static ViewAction clickChildWithId(final int id) {
    return new ViewAction() {
      @Override
      public Matcher<View> getConstraints() {
        return null;
      }

      @Override
      public String getDescription() {
        return "Click on a child View with specified id";
      }

      @Override
      public void perform(UiController uiController, View view) {
        View child = view.findViewById(id);
        child.performClick();
      }
    };
  }
}