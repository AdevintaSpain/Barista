package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertDrawerIsClosed;
import static com.schibsted.spain.barista.BaristaAssertions.assertDrawerIsOpen;
import static com.schibsted.spain.barista.BaristaAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.BaristaNavigationDrawerActions.closeDrawer;
import static com.schibsted.spain.barista.BaristaNavigationDrawerActions.openDrawer;

@RunWith(AndroidJUnit4.class)
public class NavigationDrawerActivityTest {

  @Rule
  public ActivityTestRule<NavigationDrawerActivity> activityRule = new ActivityTestRule<>(NavigationDrawerActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void openAndCloseDrawer() {
    openDrawer(R.id.drawer);
    assertDisplayed("menu item");
    assertDrawerIsOpen(R.id.drawer);

    closeDrawer(R.id.drawer);
    assertNotDisplayed("menu item");
    assertDrawerIsClosed(R.id.drawer);
  }
}
