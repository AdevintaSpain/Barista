package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsNotDisplayed;
import static com.schibsted.spain.barista.BaristaNavigationDrawerActions.closeDrawer;
import static com.schibsted.spain.barista.BaristaNavigationDrawerActions.drawerIsClosed;
import static com.schibsted.spain.barista.BaristaNavigationDrawerActions.drawerIsOpen;
import static com.schibsted.spain.barista.BaristaNavigationDrawerActions.openDrawer;


@RunWith(AndroidJUnit4.class)
public class NavigationDrawerActivityTest {

    @Rule
    public ActivityTestRule<NavigationDrawerActivity> activityRule = new ActivityTestRule<>(NavigationDrawerActivity.class);

    @Test
    public void openAndCloseDrawer() {
        openDrawer(R.id.drawer_layout);
        assertTextIsDisplayed("menu item");
        drawerIsOpen(R.id.drawer_layout);

        closeDrawer(R.id.drawer_layout);
        assertTextIsNotDisplayed("menu item");
        drawerIsClosed(R.id.drawer_layout);
    }
}
