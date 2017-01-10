package com.schibsted.spain.barista;


import static com.schibsted.spain.barista.BaristaClickActions.click;

public class BaristaDialogActions {

    public static void clickDialogPositiveButton() {
        click(android.R.id.button1);
    }

    public static void clickDialogNegativeButton() {
        click(android.R.id.button2);
    }

    public static void clickDialogNeutralButton() {
        click(android.R.id.button3);
    }
}
