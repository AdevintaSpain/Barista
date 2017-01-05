package com.schibsted.spain.barista;

import android.support.annotation.IdRes;

import static com.schibsted.spain.barista.BaristaClickActions.click;

public class BaristaCheckBoxActions {

    public static void clickCheckBoxItem(@IdRes int checkBoxId) {
        click(checkBoxId);
    }

    public static void clickCheckBoxItem(String text) {
        click(text);
    }
}
