package com.schibsted.spain.barista.custom;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import org.hamcrest.Matcher;

public class CustomConstraintViewAction implements ViewAction {

  private final ViewAction viewAction;
  private final Matcher<View> constraint;

  public CustomConstraintViewAction(ViewAction viewAction, Matcher<View> constraint) {
    this.viewAction = viewAction;
    this.constraint = constraint;
  }

  @Override
  public Matcher<View> getConstraints() {
    return constraint;
  }

  @Override
  public String getDescription() {
    return viewAction.getDescription();
  }

  @Override
  public void perform(UiController uiController, View view) {
    viewAction.perform(uiController, view);
  }
}
