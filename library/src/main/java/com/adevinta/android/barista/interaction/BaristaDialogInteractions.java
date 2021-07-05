package com.adevinta.android.barista.interaction;

import static com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn;

public class BaristaDialogInteractions {

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
