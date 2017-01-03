package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.contrib.RecyclerViewActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class BaristaRecyclerViewActions {

    public static void clickRecyclerViewItem(@IdRes int recyclerViewId, int position) {
        onView(withId(recyclerViewId))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
    }

}
