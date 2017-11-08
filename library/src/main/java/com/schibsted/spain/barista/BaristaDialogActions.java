package com.schibsted.spain.barista;

import static com.schibsted.spain.barista.BaristaClickActions.clickOn;

public class BaristaDialogActions {

  public static void clickDialogPositiveButton() {
    BaristaClickActions.clickOn(android.R.id.button1);
  }

  public static void clickDialogNegativeButton() {
    BaristaClickActions.clickOn(android.R.id.button2);
  }

  public static void clickDialogNeutralButton() {
    BaristaClickActions.clickOn(android.R.id.button3);
  }
}
