package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.internal.failurehandler.BaristaException;
import com.adevinta.android.barista.internal.util.BaristaResourceTypeException;
import junit.framework.AssertionFailedError;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaAssertions.assertThatBackButtonClosesTheApp;
import static com.adevinta.android.barista.assertion.BaristaBackgroundAssertions.assertHasAnyBackground;
import static com.adevinta.android.barista.assertion.BaristaBackgroundAssertions.assertHasBackground;
import static com.adevinta.android.barista.assertion.BaristaBackgroundAssertions.assertHasNoBackground;
import static com.adevinta.android.barista.assertion.BaristaCheckedAssertions.assertChecked;
import static com.adevinta.android.barista.assertion.BaristaCheckedAssertions.assertUnchecked;
import static com.adevinta.android.barista.assertion.BaristaClickableAssertions.assertClickable;
import static com.adevinta.android.barista.assertion.BaristaClickableAssertions.assertNotClickable;
import static com.adevinta.android.barista.assertion.BaristaEnabledAssertions.assertDisabled;
import static com.adevinta.android.barista.assertion.BaristaEnabledAssertions.assertEnabled;
import static com.adevinta.android.barista.assertion.BaristaFocusedAssertions.assertFocused;
import static com.adevinta.android.barista.assertion.BaristaFocusedAssertions.assertNotFocused;
import static com.adevinta.android.barista.assertion.BaristaImageViewAssertions.assertHasAnyDrawable;
import static com.adevinta.android.barista.assertion.BaristaImageViewAssertions.assertHasDrawable;
import static com.adevinta.android.barista.assertion.BaristaImageViewAssertions.assertHasNoDrawable;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertContains;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotContains;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static junit.framework.Assert.fail;

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
  public void checkClickableView() {
    assertClickable(R.id.enabled_button);
    assertClickable(R.string.enabled_button);
    assertClickable("Enabled button");
  }

  @Test
  public void checkClickableView_breaksWhenNeeded() {
    try {
      assertClickable(R.id.disabled_button);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertClickable(R.string.disabled_button);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertClickable("Disabled button");
      fail();
    } catch (Throwable expected) {
    }
  }

  @Test
  public void checkNonClickableView() {
    assertNotClickable(R.id.disabled_button);
    assertNotClickable(R.string.disabled_button);
    assertNotClickable("Disabled button");
  }

  @Test
  public void checkNonClickableView_breaksWhenNeeded() {
    try {
      assertNotClickable(R.id.enabled_button);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertNotClickable(R.string.enabled_button);
      fail();
    } catch (Throwable expected) {
    }
    try {
      assertNotClickable("Enabled button");
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

  @Test
  public void checkMaterialButtonDrawable_withId() throws Exception {
    assertHasDrawable(R.id.material_button_view, R.drawable.ic_barista);
  }

  @Test
  public void checkVectorDrawable_withId() throws Exception {
    assertHasDrawable(R.id.vector_image_view, R.drawable.barista_logo_vector);
  }

  @Test
  public void checkVectorDrawableFromCode_withId() throws Exception {
    assertHasDrawable(R.id.code_vector_image_view, R.drawable.barista_logo_vector);
  }

  @Test(expected = BaristaException.class)
  public void checkDrawable_withId_failure() throws Exception {
    assertHasDrawable(R.id.image_view, R.drawable.ic_action_menu);
  }

  @Test
  public void checkDrawable_withAnyDrawable() throws Exception {
    assertHasAnyDrawable(R.id.image_view);
  }

  @Test
  public void checkVectorDrawable_withAnyDrawable() throws Exception {
    assertHasAnyDrawable(R.id.vector_image_view);
  }

  @Test
  public void checkVectorDrawableFromCode_withAnyDrawable() throws Exception {
    assertHasAnyDrawable(R.id.code_vector_image_view);
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
  public void checkBackgroundDrawable_withId() throws Exception {
    assertHasBackground(R.id.view_with_drawable_background, R.drawable.ic_barista);
  }

  @Test
  public void checkBackgroundGradientDrawable_withId() throws Exception {
    assertHasBackground(R.id.view_with_gradient_drawable_background, R.drawable.gradient_drawable);
  }

  @Test
  public void checkBackgroundColor_withId() throws Exception {
    assertHasBackground(R.id.view_with_color_background, R.color.red);
  }

  @Test(expected = BaristaException.class)
  public void checkBackgroundDrawable_withId_failure() throws Exception {
    assertHasBackground(R.id.view_without_background, R.drawable.ic_action_menu);
  }

  @Test(expected = BaristaException.class)
  public void checkBackgroundGradientDrawable_withId_failure() throws Exception {
    assertHasBackground(R.id.view_without_background, R.drawable.gradient_drawable);
  }

  @Test(expected = BaristaException.class)
  public void checkBackgroundColor_withId_failure() throws Exception {
    assertHasBackground(R.id.view_with_color_background, R.color.blue);
  }

  @Test
  public void checkBackground_withAnyDrawable() throws Exception {
    assertHasAnyBackground(R.id.view_with_drawable_background);
  }

  @Test(expected = BaristaException.class)
  public void checkBackground_withAnyDrawable_failure() throws Exception {
    assertHasAnyBackground(R.id.view_without_background);
  }

  @Test
  public void checkBackground_withoutDrawable() throws Exception {
    assertHasNoBackground(R.id.view_without_background);
  }

  @Test(expected = BaristaException.class)
  public void checkBackground_withoutDrawable_failure() throws Exception {
    assertHasNoBackground(R.id.view_with_drawable_background);
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
    assertContains(R.string.enabled);
  }

  @Test(expected = BaristaException.class)
  public void checkTextViewContainsText_withViewId_failsWhenNeeded() {
    assertContains(R.id.enabled_button, "Disabled");
  }

  @Test(expected = BaristaException.class)
  public void checkTextViewContainsResourceText_withViewId_failsWhenNeeded() {
    assertContains(R.id.enabled_button, R.string.unexising_text);
  }

  @Test
  public void checkTextViewContainsText_withoutViewId() {
    assertContains("Enabled");
    assertContains(R.string.enabled);
  }

  @Test(expected = BaristaException.class)
  public void checkTextViewContainsText_withoutViewId_failsWhenNeeded() {
    assertContains("unexisting text");
  }

  @Test(expected = BaristaException.class)
  public void checkTextViewContainsResourceText_withoutViewId_failsWhenNeeded() {
    assertContains(R.string.unexising_text);
  }

  @Test
  public void checkTextViewDoesntContainsText_withViewId() {
    assertNotContains(R.id.enabled_button, "unexisting text");
    assertNotContains(R.id.enabled_button, R.string.unexising_text);
  }

  @Test(expected = AssertionFailedError.class)
  public void checkTextViewDoesntContainsText_withViewId_failsWhenNeeded() {
    assertNotContains(R.id.enabled_button, "Enabled");
  }

  @Test
  public void checkTextViewDoesntContainsText_withoutViewId() {
    assertNotContains("unexisting text");
    assertNotContains(R.string.unexising_text);
  }

  @Test(expected = AssertionFailedError.class)
  public void checkTextViewDoesntContainsText_withoutViewId_failsWhenNeeded() {
    assertNotContains("Enabled");
  }

  @Test(expected = AssertionFailedError.class)
  public void checkTextViewDoesntContainsResourceText_withoutViewId_failsWhenNeeded() {
    assertNotContains(R.string.enabled);
  }

  @Test(expected = BaristaException.class)
  public void checkVectorDrawable_fails() {
    assertHasDrawable(R.id.vector_image_view, R.drawable.barista_logo_vector_2);
  }

  @Test(expected = BaristaException.class)
  public void checkAnyDrawable_fails() {
    assertHasAnyDrawable(R.id.no_drawable_image);
  }
}
