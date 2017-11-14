package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.view.GravityCompat;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.action.BaristaDrawerActions.closeDrawer;
import static com.schibsted.spain.barista.action.BaristaDrawerActions.closeDrawerWithGravity;
import static com.schibsted.spain.barista.action.BaristaDrawerActions.openDrawer;
import static com.schibsted.spain.barista.action.BaristaDrawerActions.openDrawerWithGravity;
import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaDrawerAssertions.assertDrawerIsClosed;
import static com.schibsted.spain.barista.assertion.BaristaDrawerAssertions.assertDrawerIsClosedWithGravity;
import static com.schibsted.spain.barista.assertion.BaristaDrawerAssertions.assertDrawerIsOpen;
import static com.schibsted.spain.barista.assertion.BaristaDrawerAssertions.assertDrawerIsOpenWithGravity;

@RunWith(AndroidJUnit4.class)
public class NavigationDrawerActivityTest {

  @Rule
  public ActivityTestRule<NavigationDrawerActivity> activityRule = new ActivityTestRule<>(NavigationDrawerActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void openAndCloseDrawerById() {
    openDrawer(R.id.drawer);
    assertDisplayed("menu item");
    assertDrawerIsOpen(R.id.drawer);

    closeDrawer(R.id.drawer);
    assertNotDisplayed("menu item");
    assertDrawerIsClosed(R.id.drawer);
  }

  @Test
  public void openAndCloseTheOnlyDrawer() {
    openDrawer();
    assertDisplayed("menu item");
    assertDrawerIsOpen();

    closeDrawer();
    assertNotDisplayed("menu item");
    assertDrawerIsClosed();
  }

  @Test
  public void openAndCloseDrawerAtEndGravity() {
    openDrawerWithGravity(GravityCompat.END);
    assertDisplayed("right drawer");
    assertDrawerIsOpenWithGravity(GravityCompat.END);

    closeDrawerWithGravity(GravityCompat.END);
    assertNotDisplayed("right drawer");
    assertDrawerIsClosedWithGravity(GravityCompat.END);
  }
}
