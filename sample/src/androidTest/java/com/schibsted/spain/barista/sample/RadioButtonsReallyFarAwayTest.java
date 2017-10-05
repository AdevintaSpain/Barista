package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.util.FailureHandlerValidatorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.BaristaRadioButtonActions.clickRadioButtonItem;
import static com.schibsted.spain.barista.BaristaRadioButtonActions.clickRadioButtonPosition;

@RunWith(AndroidJUnit4.class)
public class RadioButtonsReallyFarAwayTest {

  @Rule
  public ActivityTestRule<RadioButtonsActivity> activityRule =
      new ActivityTestRule<>(RadioButtonsActivity.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void farAwayRadioButton_isNotDisplayedIfNotScrolling() {
    assertNotDisplayed(R.id.radiogroup_really_far_away);
  }

  @Test
  public void checkRadioButtonsById_withFirstOption() {
    clickRadioButtonItem(R.id.radiogroup_really_far_away, R.id.first_item_really_far_away);
    assertDisplayed("" + R.id.first_item_really_far_away);
  }

  @Test
  public void checkRadioButtonsById_withLastOption() {
    clickRadioButtonItem(R.id.radiogroup_really_far_away, R.id.last_item_really_far_away);
    assertDisplayed("" + R.id.last_item_really_far_away);
  }

  @Test
  public void checkRadioButtonsByText_withFirstOption() {
    clickRadioButtonItem(R.id.radiogroup_really_far_away, "First far away option");
    assertDisplayed("" + R.id.first_item_really_far_away);
  }

  @Test
  public void checkRadioButtonsByText_withLastOption() {
    clickRadioButtonItem(R.id.radiogroup_really_far_away, "Last far away option");
    assertDisplayed("" + R.id.last_item_really_far_away);
  }

  @Test
  public void checkRadioButtonsByPosition_withFirstOption() {
    clickRadioButtonPosition(R.id.radiogroup_really_far_away, 0);
    assertDisplayed("" + R.id.first_item_really_far_away);
  }

  @Test
  public void checkRadioButtonsByPosition_withLastOption() {
    clickRadioButtonPosition(R.id.radiogroup_really_far_away, 1);
    assertDisplayed("" + R.id.last_item_really_far_away);
  }
}