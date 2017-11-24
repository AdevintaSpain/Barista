package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.internal.util.BaristaArgumentTypeException;

import junit.framework.AssertionFailedError;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertThatBackButtonClosesTheApp;
import static com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertChecked;
import static com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertUnchecked;
import static com.schibsted.spain.barista.assertion.BaristaEnabledAssertions.assertDisabled;
import static com.schibsted.spain.barista.assertion.BaristaEnabledAssertions.assertEnabled;
import static com.schibsted.spain.barista.assertion.BaristaFocusedAssertions.assertFocused;
import static com.schibsted.spain.barista.assertion.BaristaFocusedAssertions.assertNotFocused;
import static com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasAnyDrawable;
import static com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable;
import static com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasNoDrawable;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class AssertionsTest {

  @Rule
  public ActivityTestRule<SomeViewsWithDifferentVisibilitiesActivity> activityRule =
      new ActivityTestRule<>(SomeViewsWithDifferentVisibilitiesActivity.class);

  @Test
  public void checkVisibleViews() {
    assertDisplayed(R.id.visible_view);

    assertDisplayed(R.string.hello_world);
    assertDisplayed("Hello world!");
  }

  @Test
  public void checkVisibleViews_breaksWhenNeeded() {
    try {
      assertDisplayed(R.id.invisible_view);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertDisplayed(R.string.unknown);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertDisplayed("Unknown");
      fail();
    } catch (Throwable expected) {
    }
  }

  @Test
  public void checkVisible_withRepeatedViews() throws Exception {
    assertNotDisplayed(R.id.repeated_view_1_gone);

    assertDisplayed("Repeated");
    assertDisplayed(R.string.repeated);
  }

  @Test
  public void checkExpectedText() throws Exception {
    assertDisplayed(R.id.visible_view, "Hello world!");
  }

  @Test(expected = AssertionFailedError.class)
  public void checkExpectedText_failsWhenTextIsNotTheExpected() throws Exception {
    assertDisplayed(R.id.visible_view, "This is not the text you are looking for");
  }

  @Test
  public void checkInvisibleViews() {
    assertNotDisplayed(R.id.invisible_view);
    assertNotDisplayed(R.id.gone_view);

    assertNotDisplayed(R.string.im_invisible);
    assertNotDisplayed("I'm invisible!");
  }

  @Test
  public void checkInvisibleViews_breaksWhenNeeded() {
    try {
      assertNotDisplayed(R.id.visible_view);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertNotDisplayed(R.string.hello_world);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertNotDisplayed("Hello world!");
      fail();
    } catch (Throwable expected) {
    }
  }

  @Test
  public void checkUnexistingView() {
    assertNotExist(R.id.view_in_another_layout);

    assertNotExist(R.string.unknown);
    assertNotExist("Unknown");
  }

  @Test
  public void checkUnexistingView_breaksWhenNeeded() {
    try {
      assertNotExist(R.id.visible_view);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertNotExist(R.string.hello_world);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertNotExist("Hello world!");
      fail();
    } catch (Throwable expected) {
    }
  }

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
    } catch (BaristaArgumentTypeException expected) {
    }
    try {
      assertNotDisplayed(R.color.colorAccent);
      fail();
    } catch (BaristaArgumentTypeException expected) {
    }
    try {
      assertNotExist(R.color.colorAccent);
      fail();
    } catch (BaristaArgumentTypeException expected) {
    }
    try {
      assertEnabled(R.color.colorAccent);
      fail();
    } catch (BaristaArgumentTypeException expected) {
    }
    try {
      assertDisabled(R.color.colorAccent);
      fail();
    } catch (BaristaArgumentTypeException expected) {
    }
    try {
      assertFocused(R.color.colorAccent);
      fail();
    } catch (BaristaArgumentTypeException expected) {
    }
    try {
      assertNotFocused(R.color.colorAccent);
      fail();
    } catch (BaristaArgumentTypeException expected) {
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

  @Test(expected = AssertionFailedError.class)
  public void checkDrawable_withId_failure() throws Exception {
    assertHasDrawable(R.id.image_view, R.drawable.ic_action_menu);
  }

  @Test
  public void checkDrawable_withAnyDrawable() throws Exception {
    assertHasAnyDrawable(R.id.image_view);
  }

  @Test(expected = AssertionFailedError.class)
  public void checkDrawable_withAnyDrawable_failure() throws Exception {
    assertHasAnyDrawable(R.id.image_view_without_image);
  }

  @Test
  public void checkDrawable_withoutDrawable() throws Exception {
    assertHasNoDrawable(R.id.image_view_without_image);
  }

  @Test(expected = AssertionFailedError.class)
  public void checkDrawable_withoutDrawable_failure() throws Exception {
    assertHasNoDrawable(R.id.image_view);
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
}
