package com.schibsted.spain.barista.sample.assertion;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.internal.failurehandler.BaristaException;
import com.schibsted.spain.barista.sample.R;
import com.schibsted.spain.barista.sample.SomeViewsWithDifferentVisibilitiesActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;

@RunWith(AndroidJUnit4.class)
public class VisibilityAssertionsTest {

  @Rule
  public ActivityTestRule<SomeViewsWithDifferentVisibilitiesActivity> activityRule =
      new ActivityTestRule<>(SomeViewsWithDifferentVisibilitiesActivity.class);

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void checkDisplayedViews() {
    assertDisplayed(R.id.visible_view);

    assertDisplayed(R.string.hello_world);
    assertDisplayed("Hello world!");
  }

  @Test
  public void checkDisplayed_withRepeatedViews() {
    assertDisplayed("Repeated");
    assertDisplayed(R.string.repeated);
  }

  @Test
  public void checkNotDisplayed_withGoneView() {
    assertNotDisplayed(R.id.repeated_view_1_gone);
  }

  @Test
  public void checkDisplayedViews_failsWhenInvisible() {
    thrown.expect(BaristaException.class);

    assertDisplayed(R.id.invisible_view);
  }

  @Test
  public void checkDisplayed_failsWhenNotExists() {
    thrown.expect(BaristaException.class);

    assertDisplayed(R.string.not_exists);
  }

  @Test
  public void checkDisplayedIdAndText() {
    assertDisplayed(R.id.visible_view, "Hello world!");
  }

  @Test
  public void checkDisplayedIdAndText_failsWhenTextIsNotTheExpected() {
    thrown.expect(BaristaException.class);

    assertDisplayed(R.id.visible_view, "This is not the text you are looking for");
  }

  @Test
  public void checkDisplayedIdAndText_failsWhenViewDoesNotExist() {
    thrown.expect(BaristaException.class);

    assertDisplayed(R.id.not_exists, "This is not the text you are looking for");
  }

  @Test
  public void checkNotDisplayed_withInvisibleAndGoneViews() {
    assertNotDisplayed(R.id.invisible_view);
    assertNotDisplayed(R.id.gone_view);
    assertNotDisplayed(R.string.im_invisible);
    assertNotDisplayed("I'm invisible!");
  }

  @Test
  public void checkNotDisplayedIdAndText_whenTextDoesNotMatch() {
    assertNotDisplayed(R.id.visible_view, "This text must not be displayed on the view");
  }

  @Test
  public void checkNotDisplayedIdAndText_failsWhenTextMatches() {
    thrown.expect(BaristaException.class);

    assertNotDisplayed(R.id.visible_view, "Hello world!");
  }

  @Test
  public void checkNotDisplayed_failsWhenVisible() {
    thrown.expect(BaristaException.class);

    assertNotDisplayed(R.id.visible_view);
  }

  @Test
  public void checkNotExist() {
    assertNotExist(R.id.view_in_another_layout);
    assertNotExist(R.string.not_exists);
    assertNotExist("Unknown");
  }

  @Test
  public void checkNotExist_failsWhenViewExists() {
    thrown.expect(AssertionError.class);

    assertNotExist(R.id.visible_view);
  }
}
