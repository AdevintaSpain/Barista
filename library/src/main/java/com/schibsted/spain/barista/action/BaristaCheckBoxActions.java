package com.schibsted.spain.barista.action;

import android.support.annotation.IdRes;

import static com.schibsted.spain.barista.action.BaristaClickActions.clickOn;

public class BaristaCheckBoxActions {

  public static void clickCheckBoxItem(@IdRes int checkBoxId) {
    clickOn(checkBoxId);
  }

  public static void clickCheckBoxItem(String text) {
    clickOn(text);
  }
}
