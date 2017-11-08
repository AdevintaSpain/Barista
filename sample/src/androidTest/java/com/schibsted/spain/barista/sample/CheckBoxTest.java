package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaCheckBoxActions.clickCheckBoxItem;

@RunWith(AndroidJUnit4.class)
public class CheckBoxTest {

  @Rule
  public ActivityTestRule<CheckBoxActivity> activityRule = new ActivityTestRule<>(CheckBoxActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void checkCheckBoxClickById_firstItem() {
    clickCheckBoxItem(R.id.first_item);
    assertDisplayed("" + R.id.first_item);
  }

  @Test
  public void checkCheckBoxClickById_secondItem() {
    clickCheckBoxItem(R.id.second_item);
    assertDisplayed("" + R.id.second_item);
  }

  @Test
  public void checkCheckBoxClickByText_firstItem() {
    clickCheckBoxItem("Hello");
    assertDisplayed("" + R.id.first_item);
  }

  @Test
  public void checkCheckBoxClickByText_secondItem() {
    clickCheckBoxItem("Bye bye");
    assertDisplayed("" + R.id.second_item);
  }
}