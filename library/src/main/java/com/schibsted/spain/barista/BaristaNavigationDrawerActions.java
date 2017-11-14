package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.contrib.DrawerActions;

public class BaristaNavigationDrawerActions {

  public static void openDrawer(@IdRes int id) {
    DrawerActions.openDrawer(id);
  }

  public static void closeDrawer(@IdRes int id) {
    DrawerActions.closeDrawer(id);
  }
}
