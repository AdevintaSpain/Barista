package com.adevinta.android.barista.sample;

import androidx.test.filters.FlakyTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.adevinta.android.barista.internal.failurehandler.BaristaException;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaMenuClickInteractions.clickMenu;
import static com.adevinta.android.barista.interaction.BaristaMenuClickInteractions.openMenu;


public class MenuClickTest {

  @Rule
  public ActivityTestRule<MenuActivity> activityRule = new ActivityTestRule<>(MenuActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void textMenuClick_byId() {
    clickMenu(R.id.menu_action_1);
    assertDisplayed("First menu option");
  }

  @Test
  public void textMenuClick_byTitle() {
    clickMenu("Menu 1");
    assertDisplayed("First menu option");
  }

  @Test
  public void iconMenuClick_byId() {
    clickMenu(R.id.menu_action_2);
    assertDisplayed("Second menu option");
  }

  @Test
  public void iconMenuClick_byTitle() {
    clickMenu("Menu 2");
    assertDisplayed("Second menu option");
  }

  @Test
  @FlakyTest(detail = "This tests passes on local, but fails on CI")
  public void overflowMenuClick_byId() {
    clickMenu(R.id.menu_action_3);
    assertDisplayed("Third menu option");
  }

  @Test
  @FlakyTest(detail = "This tests passes on local, but fails on CI")
  public void overflowMenuClick_byTitle() {
    clickMenu("Menu 3");
    assertDisplayed("Third menu option");
  }

  @Test
  @FlakyTest(detail = "This tests passes on local, but fails on CI")
  public void openOverflowMenu_withoutClickingAnyOption() {
    openMenu();
    assertDisplayed("Menu 3");
    assertDisplayed("Menu 4");
  }

  @Test(expected = BaristaException.class)
  public void missingMenuClickFails_byId() throws Exception {
    clickMenu(R.id.button);
  }

  @Test(expected = BaristaException.class)
  public void missingMenuClickFails_byTitle() throws Exception {
    clickMenu("Non existing menu");
  }
}
