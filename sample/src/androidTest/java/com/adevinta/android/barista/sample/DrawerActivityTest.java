package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.core.view.GravityCompat;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.adevinta.android.barista.assertion.BaristaDrawerAssertions.assertDrawerIsClosed;
import static com.adevinta.android.barista.assertion.BaristaDrawerAssertions.assertDrawerIsClosedWithGravity;
import static com.adevinta.android.barista.assertion.BaristaDrawerAssertions.assertDrawerIsOpen;
import static com.adevinta.android.barista.assertion.BaristaDrawerAssertions.assertDrawerIsOpenWithGravity;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.adevinta.android.barista.interaction.BaristaDrawerInteractions.closeDrawer;
import static com.adevinta.android.barista.interaction.BaristaDrawerInteractions.closeDrawerWithGravity;
import static com.adevinta.android.barista.interaction.BaristaDrawerInteractions.openDrawer;
import static com.adevinta.android.barista.interaction.BaristaDrawerInteractions.openDrawerWithGravity;

@RunWith(AndroidJUnit4.class)
public class DrawerActivityTest {

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
