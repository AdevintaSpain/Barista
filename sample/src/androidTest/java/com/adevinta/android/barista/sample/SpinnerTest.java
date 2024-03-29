package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.interaction.BaristaSpinnerInteractions.clickSpinnerItem;

public class SpinnerTest {

  @Rule
  public ActivityTestRule<SpinnerActivity> activityRule = new ActivityTestRule<>(SpinnerActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkSelectSpinnerPosition_withoutExplicitViewModel() {
    clickSpinnerItem(R.id.spinner, 0);
    assertDisplayed("Banana");
  }

  @Test
  public void checkSelectSpinnerThirdPosition_withoutExplicitViewModel() {
    clickSpinnerItem(R.id.spinner, 2);
    assertDisplayed("Orange");
  }

  @Test
  public void checkSelectSpinnerPosition() {
    clickSpinnerItem(R.id.spinner, String.class, 0);
    assertDisplayed("Banana");
  }

  @Test
  public void checkSelectSpinnerThirdPosition() {
    clickSpinnerItem(R.id.spinner, String.class, 2);
    assertDisplayed("Orange");
  }
}
