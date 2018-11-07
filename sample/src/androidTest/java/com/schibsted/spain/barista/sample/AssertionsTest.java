package com.schibsted.spain.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.internal.failurehandler.BaristaException;
import com.schibsted.spain.barista.internal.util.BaristaResourceTypeException;
import junit.framework.AssertionFailedError;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertThatBackButtonClosesTheApp;
import static com.schibsted.spain.barista.assertion.BaristaBackgroundAssertions.assertHasAnyBackground;
import static com.schibsted.spain.barista.assertion.BaristaBackgroundAssertions.assertHasBackground;
import static com.schibsted.spain.barista.assertion.BaristaBackgroundAssertions.assertHasNoBackground;
import static com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertChecked;
import static com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertUnchecked;
import static com.schibsted.spain.barista.assertion.BaristaEnabledAssertions.assertDisabled;
import static com.schibsted.spain.barista.assertion.BaristaEnabledAssertions.assertEnabled;
import static com.schibsted.spain.barista.assertion.BaristaFocusedAssertions.assertFocused;
import static com.schibsted.spain.barista.assertion.BaristaFocusedAssertions.assertNotFocused;
import static com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasAnyDrawable;
import static com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable;
import static com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasNoDrawable;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertContains;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotContains;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class AssertionsTest {

  @Rule
  public ActivityTestRule<SomeViewsWithDifferentVisibilitiesActivity> activityRule =
      new ActivityTestRule<>(SomeViewsWithDifferentVisibilitiesActivity.class);

  @Test
  public void checkEnabledView() {
    assertEnabled(R.id.enabled_button);

    assertEnabled(R.string.enabled_button);
    assertEnabled("Enabled button");
  }

  @Test
  public void checkEnabledView_breaksWhenNeeded() {
    try {
      assertEnabled(R.id.disabled_button);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertEnabled(R.string.disabled_button);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertEnabled("Disabled button");
      fail();
    } catch (Throwable expected) {
    }
  }

  @Test
  public void checkDisabledView() {
    assertDisabled(R.id.disabled_button);

    assertDisabled(R.string.disabled_button);
    assertDisabled("Disabled button");
  }

  @Test
  public void checkDisabledView_breaksWhenNeeded() {
    try {
      assertDisabled(R.id.enabled_button);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertDisabled(R.string.enabled_button);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertDisabled("Enabled button");
      fail();
    } catch (Throwable expected) {
    }
  }

  @Test
  public void checkCheckedView() {
    assertChecked(R.id.checked_checkbox);
    assertChecked(R.string.checked_checkbox);
    assertChecked("Checked checkbox");
  }

  @Test
  public void checkCheckedView_breaksWhenNeeded() {
    try {
      assertChecked(R.id.unchecked_checkbox);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertChecked(R.string.unchecked_checkbox);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertChecked("Unchecked checkbox");
      fail();
    } catch (Throwable expected) {
    }
  }

  @Test
  public void checkUncheckedView() {
    assertUnchecked(R.id.unchecked_checkbox);
    assertUnchecked(R.string.unchecked_checkbox);
    assertUnchecked("Unchecked checkbox");
  }

  @Test
  public void checkUncheckedView_breaksWhenNeeded() {
    try {
      assertChecked(R.id.checked_checkbox);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertChecked(R.string.checked_checkbox);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertChecked("Checked checkbox");
      fail();
    } catch (Throwable expected) {
    }
  }

  @Test
  public void checkNonCheckableView_breaksWhenCheckStateAsserted() {
    try {
      assertChecked(R.id.button);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertUnchecked(R.id.button);
      fail();
    } catch (Throwable expected) {
    }
  }

  @Test
  public void methodsByIdLaunchExceptionWhenThePassedIdIsNotAResIdOrAStringId() {
    try {
      assertDisplayed(R.color.colorAccent);
      fail();
    } catch (BaristaResourceTypeException expected) {
    }
    try {
      assertNotDisplayed(R.color.colorAccent);
      fail();
    } catch (BaristaResourceTypeException expected) {
    }
    try {
      assertNotExist(R.color.colorAccent);
      fail();
    } catch (BaristaResourceTypeException expected) {
    }
    try {
      assertEnabled(R.color.colorAccent);
      fail();
    } catch (BaristaResourceTypeException expected) {
    }
    try {
      assertDisabled(R.color.colorAccent);
      fail();
    } catch (BaristaResourceTypeException expected) {
    }
    try {
      assertFocused(R.color.colorAccent);
      fail();
    } catch (BaristaResourceTypeException expected) {
    }
    try {
      assertNotFocused(R.color.colorAccent);
      fail();
    } catch (BaristaResourceTypeException expected) {
    }
  }

  @Test
  public void checkBackButton() {
    assertThatBackButtonClosesTheApp();
  }

  @Test
  public void checkDrawable_withId() throws Exception {
    assertHasDrawable(R.id.image_view, R.drawable.ic_barista);
  }

  @Test(expected = BaristaException.class)
  public void checkDrawable_withId_failure() throws Exception {
    assertHasDrawable(R.id.image_view, R.drawable.ic_action_menu);
  }

  @Test
  public void checkDrawable_withAnyDrawable() throws Exception {
    assertHasAnyDrawable(R.id.image_view);
  }

  @Test(expected = BaristaException.class)
  public void checkDrawable_withAnyDrawable_failure() throws Exception {
    assertHasAnyDrawable(R.id.image_view_without_image);
  }

  @Test
  public void checkDrawable_withoutDrawable() throws Exception {
    assertHasNoDrawable(R.id.image_view_without_image);
  }

  @Test(expected = BaristaException.class)
  public void checkDrawable_withoutDrawable_failure() throws Exception {
    assertHasNoDrawable(R.id.image_view);
  }

  @Test
  public void checkBackground_withId() throws Exception {
    assertHasBackground(R.id.view_with_backbround, R.drawable.ic_barista);
  }

  @Test(expected = BaristaException.class)
  public void checkBackground_withId_failure() throws Exception {
    assertHasBackground(R.id.view_without_backbround, R.drawable.ic_action_menu);
  }

  @Test
  public void checkBackground_withAnyDrawable() throws Exception {
    assertHasAnyBackground(R.id.view_with_backbround);
  }

  @Test(expected = BaristaException.class)
  public void checkBackground_withAnyDrawable_failure() throws Exception {
    assertHasAnyBackground(R.id.view_without_backbround);
  }

  @Test
  public void checkBackground_withoutDrawable() throws Exception {
    assertHasNoBackground(R.id.view_without_backbround);
  }

  @Test(expected = BaristaException.class)
  public void checkBackground_withoutDrawable_failure() throws Exception {
    assertHasNoBackground(R.id.view_with_backbround);
  }

  @Test
  public void checkViewHasFocus() throws Exception {
    assertFocused(R.id.edittext_with_focus);
    assertFocused(R.string.edittext_with_focus);
    assertFocused("EditText with focus");
  }

  @Test
  public void checkViewHasNotFocus() throws Exception {
    assertNotFocused(R.id.edittext_without_focus);
    assertNotFocused(R.string.edittext_with_no_focus);
    assertNotFocused("EditText with no focus");
  }

  @Test
  public void checkTextViewContainsText_withViewId() {
    assertContains(R.id.enabled_button, "Enabled");
  }

  @Test(expected = BaristaException.class)
  public void checkTextViewContainsText_withViewId_failsWhenNeeded() {
    assertContains(R.id.enabled_button, "Disabled");
  }

  @Test
  public void checkTextViewContainsText_withoutViewId() {
    assertContains("Enabled");
  }

  @Test(expected = BaristaException.class)
  public void checkTextViewContainsText_withoutViewId_failsWhenNeeded() {
    assertContains("unexisting text");
  }

  @Test
  public void checkTextViewDoesntContainsText_withViewId() {
    assertNotContains(R.id.enabled_button, "unexisting text");
  }

  @Test(expected = AssertionFailedError.class)
  public void checkTextViewDoesntContainsText_withViewId_failsWhenNeeded() {
    assertNotContains(R.id.enabled_button, "Enabled");
  }

  @Test
  public void checkTextViewDoesntContainsText_withoutViewId() {
    assertNotContains("unexisting text");
  }

  @Test(expected = AssertionFailedError.class)
  public void checkTextViewDoesntContainsText_withoutViewId_failsWhenNeeded() {
    assertNotContains("Enabled");
  }
}
