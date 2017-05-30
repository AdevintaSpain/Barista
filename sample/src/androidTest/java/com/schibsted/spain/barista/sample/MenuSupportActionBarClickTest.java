package com.schibsted.spain.barista.sample;

import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.flakyespresso.AllowFlaky;
import com.schibsted.spain.barista.flakyespresso.FlakyActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaMenuClickActions.clickMenu;

@RunWith(AndroidJUnit4.class)
public class MenuSupportActionBarClickTest {

  @Rule
  public FlakyActivityTestRule<MenuSupportActionBarActivity> activityRule = new FlakyActivityTestRule<>(MenuSupportActionBarActivity.class);

  @Test
  public void checkMenuClick_byId() {
    clickMenu(R.id.menu_action_1);
    assertDisplayed("First menu option");
  }

  @Test
  public void checkMenuClick_byText() {
    clickMenu("Menu 1");
    assertDisplayed("First menu option");
  }

  @Test
  public void checkMenuWithIconClick_byId() {
    clickMenu(R.id.menu_action_2);
    assertDisplayed("Second menu option");
  }

  @Test
  public void checkMenuWithIconClick_byText() {
    clickMenu("Menu 2");
    assertDisplayed("Second menu option");
  }

  @Test
  @AllowFlaky
  public void checkMenuOnOverflowClick_byId() {
    clickMenu(R.id.menu_action_3);
    assertDisplayed("Third menu option");
  }

  @Test
  @AllowFlaky
  public void checkMenuOnOverflowClick_byText() {
    clickMenu("Menu 3");
    assertDisplayed("Third menu option");
  }
}
