package com.schibsted.spain.barista.action;

import static com.schibsted.spain.barista.action.BaristaClickActions.clickOn;

public class BaristaDialogActions {

  public static void clickDialogPositiveButton() {
    clickOn(android.R.id.button1);
  }

  public static void clickDialogNegativeButton() {
    clickOn(android.R.id.button2);
  }

  public static void clickDialogNeutralButton() {
    clickOn(android.R.id.button3);
  }
}
