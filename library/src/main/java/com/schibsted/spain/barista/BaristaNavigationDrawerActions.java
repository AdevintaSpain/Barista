package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.contrib.DrawerActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class BaristaNavigationDrawerActions {

    public static void openDrawer(@IdRes int id) {
        DrawerActions.openDrawer(id);
    }

    public static void closeDrawer(@IdRes int id) {
        DrawerActions.closeDrawer(id);
    }

    public static void drawerIsOpen(@IdRes int id) {
        onView(withId(id)).check(matches(isOpen()));
    }

    public static void drawerIsClosed(@IdRes int id) {
        onView(withId(id)).check(matches(isClosed()));
    }
}
