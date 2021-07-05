package com.adevinta.android.barista.sample.assertion;

import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.internal.failurehandler.BaristaException;
import com.adevinta.android.barista.sample.R;
import com.adevinta.android.barista.sample.SomeViewsWithDifferentVisibilitiesActivity;
import com.adevinta.android.barista.sample.util.SpyFailureHandlerRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.hamcrest.CoreMatchers.is;

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
    Throwable thrown1 = catchThrowable(() -> assertDisplayed(R.id.invisible_view));
    assertThat(thrown1).isInstanceOf(BaristaException.class)
        .hasMessage("View (with id: com.adevinta.android.barista.sample:id/invisible_view) "
            + "didn't match condition (is displayed on the screen to the user)");

    Throwable thrown2 = catchThrowable(() -> assertDisplayed(R.string.im_invisible)); // test fails
    assertThat(thrown2).isInstanceOf(BaristaException.class)
        .hasMessageMatching("View \\(with string from resource id: <(\\d+)>\\[im_invisible\\] value: I'm invisible!\\) "
            + "didn't match condition \\(is displayed on the screen to the user\\)");

    Throwable thrown3 = catchThrowable(() -> assertDisplayed("I'm invisible!"));
    assertThat(thrown3).isInstanceOf(BaristaException.class)
        .hasMessage("View ((with text: is \"I'm invisible!\" "
            + "or (has parent matching: is assignable from class: class com.google.android.material.textfield.TextInputLayout "
            + "and has descendant: with text: is \"I'm invisible!\"))) didn't match condition "
            + "(is displayed on the screen to the user)");

    spyFailureHandlerRule.assertEspressoFailures(3);
  }

  @Test
  public void checkDisplayed_failsWhenNotExists() {
    Throwable thrown = catchThrowable(() -> assertDisplayed(R.id.no));
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageContaining("No view matching (with id: com.adevinta.android.barista.sample:id/no) was found");

    Throwable thrown2 = catchThrowable(() -> assertDisplayed(R.string.not_exists));
    assertThat(thrown2).isInstanceOf(BaristaException.class)
        .hasMessageMatching("No view matching \\(with string from resource id: <(\\d+)>\\[not_exists\\] value: Not exists\\) was found");

    Throwable thrown3 = catchThrowable(() -> assertDisplayed("Not exists"));
    assertThat(thrown3).isInstanceOf(BaristaException.class)
        .hasMessageContaining("No view matching ((with text: is \"Not exists\" "
            + "or (has parent matching: is assignable from class: class com.google.android.material.textfield.TextInputLayout "
            + "and has descendant: with text: is \"Not exists\"))) was found");

    spyFailureHandlerRule.assertEspressoFailures(3);
  }

  @Test
  public void checkDisplayedIdAndText() {
    assertDisplayed(R.id.visible_view, "Hello world!");
    assertDisplayed(R.id.visible_view, R.string.hello_world);

    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void checkDisplayedIdAndText_failsWhenTextIsNotTheExpected() {
    Throwable thrown = catchThrowable(() -> assertDisplayed(R.id.visible_view, R.string.not_exists));
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageMatching("View \\(with id: com.adevinta.android.barista.sample:id/visible_view\\) didn't match condition "
            + "\\(\\(is displayed on the screen to the user "
            + "and with string from resource id: <(\\d+)>\\[not_exists\\] value: Not exists\\)\\)");

    Throwable thrown2 = catchThrowable(() -> assertDisplayed(R.id.visible_view, "This is not the text you are looking for"));
    assertThat(thrown2).isInstanceOf(BaristaException.class)
        .hasMessage("View (with id: com.adevinta.android.barista.sample:id/visible_view) didn't match condition "
            + "((is displayed on the screen to the user and (with text: is \"This is not the text you are looking for\" "
            + "or (has parent matching: is assignable from class: class com.google.android.material.textfield.TextInputLayout "
            + "and has descendant: with text: is \"This is not the text you are looking for\"))))");

    spyFailureHandlerRule.assertEspressoFailures(2);
  }

  @Test
  public void checkDisplayedIdAndText_failsWhenInvisible() {
    Throwable thrown = catchThrowable(() -> assertDisplayed(R.id.invisible_view, R.string.im_invisible));
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessageMatching("View \\(with id: com.adevinta.android.barista.sample:id/invisible_view\\) didn't match condition "
            + "\\(\\(is displayed on the screen to the user and with string from resource id: <(\\d+)>\\)\\)");

    Throwable thrown2 = catchThrowable(() -> assertDisplayed(R.id.invisible_view, "I'm invisible!"));
    assertThat(thrown2).isInstanceOf(BaristaException.class)
        .hasMessage("View (with id: com.adevinta.android.barista.sample:id/invisible_view) didn't match condition "
            + "((is displayed on the screen to the user and (with text: is \"I'm invisible!\" "
            + "or (has parent matching: is assignable from class: class com.google.android.material.textfield.TextInputLayout "
            + "and has descendant: with text: is \"I'm invisible!\"))))");

    spyFailureHandlerRule.assertEspressoFailures(2);
  }

  @Test
  public void checkDisplayedIdAndText_failsWhenViewDoesNotExist() {
    Throwable thrown = catchThrowable(() -> assertDisplayed(R.id.not_exists, R.string.im_invisible));
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessage("No view matching (with id: com.adevinta.android.barista.sample:id/not_exists) was found");

    Throwable thrown2 = catchThrowable(() -> assertDisplayed(R.id.not_exists, "This is not the text you are looking for"));
    assertThat(thrown2).isInstanceOf(BaristaException.class)
        .hasMessage("No view matching (with id: com.adevinta.android.barista.sample:id/not_exists) was found");

    spyFailureHandlerRule.assertEspressoFailures(2);
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
    assertNotDisplayed(R.id.visible_view, R.string.this_text_must_not_be_displayed);

    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void checkNotDisplayedIdAndText_failsWhenTextMatches() {
    Throwable thrown = catchThrowable(() -> assertNotDisplayed(R.id.visible_view, "Hello world!"));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessage("View (with id: com.adevinta.android.barista.sample:id/visible_view) didn't match condition "
            + "(not (is displayed on the screen to the user and (with text: is \"Hello world!\" "
            + "or (has parent matching: is assignable from class: class com.google.android.material.textfield.TextInputLayout "
            + "and has descendant: with text: is \"Hello world!\"))))");
  }

  @Test
  public void checkNotDisplayed_failsWhenVisible() {
    Throwable thrown = catchThrowable(() -> assertNotDisplayed(R.id.visible_view));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessage("View (with id: com.adevinta.android.barista.sample:id/visible_view) "
            + "didn't match condition (not is displayed on the screen to the user)");
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

  @Test
  public void assertVisibleWithCustomMatcher_Fails_WhenTheMatcherDoesNotFindTheGivenPredicate() {
    String aTagValueThatDoesNotExistsInTheView = "notPresentTagValue";
    Throwable thrown = catchThrowable(() ->
        assertDisplayed(withTagValue(is(aTagValueThatDoesNotExistsInTheView))));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessage("No view matching (with tag value: is \"notPresentTagValue\") was found");
  }

  @Test
  public void assertVisibleWithCustomMatcher_Fails_WhenTheMatcherDoesFindTheGivenPredicateButViewIsHidden() {
    String nonExistingTag = "presentTagValueHidden";
    Throwable thrown = catchThrowable(() ->
        assertDisplayed(withTagValue(is(nonExistingTag))));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessage("View (with tag value: is \"presentTagValueHidden\") didn't match condition (is displayed on the screen to the user)");
  }

  @Test
  public void assertVisibleWithCustomMatcher_Succeeds_WhenTheMatcherMatchesAViewThatIsShown() {
    String existingTag = "presentTagValue";

    assertDisplayed(withTagValue(is(existingTag)));

    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void assertNotVisibleWithCustomMatcher_Fails_WhenTheMatcherDoesNotFindTheGivenPredicate() {
    String nonExistingTag = "notPresentTagValue";

    Throwable thrown = catchThrowable(() ->
        assertNotDisplayed(withTagValue(is(nonExistingTag))));

    spyFailureHandlerRule.assertEspressoFailures(1);
    assertThat(thrown).isInstanceOf(BaristaException.class)
        .hasMessage("No view matching (with tag value: is \"notPresentTagValue\") was found");
  }

  @Test
  public void assertVisibleWithCustomMatcher_Succeeds_WhenTheMatcherMatchesAViewThatIsHidden() {
    String hiddenTag = "presentTagValueHidden";

    assertNotDisplayed(withTagValue(is(hiddenTag)));

    spyFailureHandlerRule.assertNoEspressoFailures();
  }

  @Test
  public void assertVisibleWithCustomMatcher_Fails_WhenTheMatcherMatchesAViewThatIsNotHidden() {
    String shownTag = "presentTagValue";

    Throwable thrown = catchThrowable(() ->
        assertNotDisplayed(withTagValue(is(shownTag))));

    spyFailureHandlerRule.assertEspressoFailures(1);
    String expectedMessage =
        "View (with tag value: is \"presentTagValue\") didn't match condition (not is displayed on the screen to the user)";
    assertThat(thrown).isInstanceOf(BaristaException.class).hasMessage(expectedMessage);
  }
}
