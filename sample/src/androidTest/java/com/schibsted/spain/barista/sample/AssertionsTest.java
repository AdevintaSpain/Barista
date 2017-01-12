package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertEnabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertNotExist;
import static com.schibsted.spain.barista.BaristaAssertions.assertThatBackButtonClosesTheApp;

@RunWith(AndroidJUnit4.class)
public class AssertionsTest {

  @Rule
  public ActivityTestRule<SomeViewsWithDifferentVisibilitesActivity> activityRule =
      new ActivityTestRule<>(SomeViewsWithDifferentVisibilitesActivity.class);

  @Test
  public void checkVisibleViews() {
    assertDisplayed(R.id.visible_view);

    assertDisplayed(R.string.hello_world);
    assertDisplayed("Hello world!");
  }

  @Test
  public void checkInvisibleViews() {
    assertNotDisplayed(R.id.invisible_view);
    assertNotDisplayed(R.id.gone_view);

    assertNotDisplayed(R.string.im_invisible);
    assertNotDisplayed("I'm invisible!");
  }

  @Test
  public void checkUnexistingView() {
    assertNotExist(R.id.view_in_another_layout);

    assertNotExist(R.string.unknown);
    assertNotExist("Unknown");
  }

  @Test
  public void checkEnabledView() {
    assertEnabled(R.id.enabled_button);

    assertEnabled(R.string.enabled_button);
    assertEnabled("Enabled button");
  }

  @Test
  public void checkDisabledView() {
    assertDisabled(R.id.disabled_button);

    assertDisabled(R.string.disabled_button);
    assertDisabled("Disabled button");
  }

  @Test
  public void checkBackButton() {
    assertThatBackButtonClosesTheApp();
  }
}
