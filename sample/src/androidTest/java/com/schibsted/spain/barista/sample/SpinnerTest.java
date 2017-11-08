package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaSpinnerActions.clickSpinnerItem;

@RunWith(AndroidJUnit4.class)
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
