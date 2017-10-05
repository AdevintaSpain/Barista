package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import com.schibsted.spain.barista.internal.failurehandler.BaristaException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaMenuClickActions.clickMenu;

@RunWith(AndroidJUnit4.class)
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
  public void overflowMenuClick_byId() {
    clickMenu(R.id.menu_action_3);
    assertDisplayed("Third menu option");
  }

  @Test
  public void overflowMenuClick_byTitle() {
    clickMenu("Menu 3");
    assertDisplayed("Third menu option");
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
