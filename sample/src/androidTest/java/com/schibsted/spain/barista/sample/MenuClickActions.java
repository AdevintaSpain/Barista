package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaMenuClickActions.menuClick;

@RunWith(AndroidJUnit4.class)
public class MenuClickActions {

  @Rule
  public ActivityTestRule<MenuActivity> activityRule = new ActivityTestRule<>(MenuActivity.class);

  @Test
  public void checkMenuClick_byId() {
    menuClick(R.id.menu_action_1);
    assertDisplayed("First menu option");
  }

  @Test
  public void checkMenuClick_byText() {
    menuClick("Menu 1");
    assertDisplayed("First menu option");
  }

  @Test
  public void checkMenuWithIconClick_byId() {
    menuClick(R.id.menu_action_2);
    assertDisplayed("Second menu option");
  }

  @Test
  public void checkMenuWithIconClick_byText() {
    menuClick("Menu 2");
    assertDisplayed("Second menu option");
  }

  @Test
  public void checkMenuOnOverflowClick_byId() {
    menuClick(R.id.menu_action_3);
    assertDisplayed("Third menu option");
  }

  @Test
  public void checkMenuOnOverflowClick_byText() {
    menuClick("Menu 3");
    assertDisplayed("Third menu option");
  }
}
