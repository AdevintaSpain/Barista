package com.schibsted.spain.barista.sample.assertion;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.internal.failurehandler.BaristaException;
import com.schibsted.spain.barista.sample.R;
import com.schibsted.spain.barista.sample.SomeViewsWithDifferentVisibilitiesActivity;
import com.schibsted.spain.barista.sample.util.SpyFailureHandlerRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@RunWith(AndroidJUnit4.class)
public class VisibilityAssertionsTest {

  @Rule
  public ActivityTestRule<SomeViewsWithDifferentVisibilitiesActivity> activityRule =
      new ActivityTestRule<>(SomeViewsWithDifferentVisibilitiesActivity.class);

  @Rule
  public SpyFailureHandlerRule spyFailureHandlerRule = new SpyFailureHandlerRule();

  public void checkDisplayedViews() {
    assertDisplayed(R.id.visible_view);
    assertDisplayed(R.string.hello_world);
    assertDisplayed("Hello world!");

    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void checkDisplayed_withRepeatedViews() {
    assertDisplayed("Repeated");
    assertDisplayed(R.string.repeated);

    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void checkNotDisplayed_withGoneView() {
    assertNotDisplayed(R.id.repeated_view_1_gone);

    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void checkDisplayedViews_failsWhenInvisible() {
    Throwable thrown = catchThrowable(() -> assertDisplayed(R.id.invisible_view));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageContaining("invisible_view")
        .hasMessageContaining("is displayed on the screen");
  }

  @Test
  public void checkDisplayed_failsWhenNotExists() {
    Throwable thrown = catchThrowable(() -> assertDisplayed(R.string.not_exists));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageContaining("not_exists")
        .hasMessageContaining("No view matching");
  }

  @Test
  public void checkDisplayedIdAndText() {
    assertDisplayed(R.id.visible_view, "Hello world!");

    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void checkDisplayedIdAndText_failsWhenTextIsNotTheExpected() {
    Throwable thrown = catchThrowable(() -> assertDisplayed(R.id.visible_view, "This is not the text you are looking for"));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageContaining("visible_view")
        .hasMessageContaining("This is not the text you are looking for");
  }

  @Test
  public void checkDisplayedIdAndText_failsWhenViewDoesNotExist() {
    Throwable thrown = catchThrowable(() -> assertDisplayed(R.id.not_exists, "This is not the text you are looking for"));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageContaining("not_exists");
  }

  @Test
  public void checkNotDisplayed_withInvisibleAndGoneViews() {
    assertNotDisplayed(R.id.invisible_view);
    assertNotDisplayed(R.id.gone_view);
    assertNotDisplayed(R.string.im_invisible);
    assertNotDisplayed("I'm invisible!");

    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void checkNotDisplayedIdAndText_whenTextDoesNotMatch() {
    assertNotDisplayed(R.id.visible_view, "This text must not be displayed on the view");

    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void checkNotDisplayedIdAndText_failsWhenTextMatches() {
    Throwable thrown = catchThrowable(() -> assertNotDisplayed(R.id.visible_view, "Hello world!"));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageContaining("visible_view")
        .hasMessageContaining("not with text");
  }

  @Test
  public void checkNotDisplayed_failsWhenVisible() {
    Throwable thrown = catchThrowable(() -> assertNotDisplayed(R.id.visible_view));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageContaining("visible_view")
        .hasMessageContaining("not is displayed");
  }

  @Test
  public void checkNotExist() {
    assertNotExist(R.id.view_in_another_layout);
    assertNotExist(R.string.not_exists);
    assertNotExist("Unknown");

    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void checkNotExist_failsWhenViewExists() {
    Throwable thrown = catchThrowable(() -> assertNotExist(R.id.visible_view));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(AssertionError.class)
        .hasMessageContaining("View is present in the hierarchy");
  }
}
