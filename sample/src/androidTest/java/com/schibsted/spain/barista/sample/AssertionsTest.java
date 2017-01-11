package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextDoesNotExist;
import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsEnabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsNotDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertThatBackButtonClosesTheApp;
import static com.schibsted.spain.barista.BaristaAssertions.assertViewDoesNotExist;
import static com.schibsted.spain.barista.BaristaAssertions.assertViewIsDisabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertViewIsDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertViewIsEnabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertViewIsNotDisplayed;

@RunWith(AndroidJUnit4.class)
public class AssertionsTest {

  @Rule
  public ActivityTestRule<SomeViewsWithDifferentVisibilitesActivity> activityRule =
      new ActivityTestRule<>(SomeViewsWithDifferentVisibilitesActivity.class);

  @Test
  public void checkVisibleViews() {
    assertViewIsDisplayed(R.id.visible_view);

    assertTextIsDisplayed(R.string.hello_world);
    assertTextIsDisplayed("Hello world!");
  }

  @Test
  public void checkInvisibleViews() {
    assertViewIsNotDisplayed(R.id.invisible_view);
    assertViewIsNotDisplayed(R.id.gone_view);

    assertTextIsNotDisplayed(R.string.im_invisible);
    assertTextIsNotDisplayed("I'm invisible!");
  }

  @Test
  public void checkUnexistingView() {
    assertViewDoesNotExist(R.id.view_in_another_layout);

    assertTextDoesNotExist(R.string.unknown);
    assertTextDoesNotExist("Unknown");
  }

  @Test
  public void checkEnabledView() {
    assertViewIsEnabled(R.id.enabled_button);

    assertTextIsEnabled(R.string.enabled_button);
    assertTextIsEnabled("Enabled button");
  }

  @Test
  public void checkDisabledView() {
    assertViewIsDisabled(R.id.disabled_button);

    assertTextIsDisabled(R.string.disabled_button);
    assertTextIsDisabled("Disabled button");
  }

  @Test
  public void checkBackButton() {
    assertThatBackButtonClosesTheApp();
  }
}
