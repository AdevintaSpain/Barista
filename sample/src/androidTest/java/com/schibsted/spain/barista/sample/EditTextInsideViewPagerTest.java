package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaEditTextActions.writeToEditText;

@RunWith(AndroidJUnit4.class)
public class EditTextInsideViewPagerTest {

  @Rule
  public ActivityTestRule<ViewPagerWithFiveSamePagesActivity> activityRule =
      new ActivityTestRule<>(ViewPagerWithFiveSamePagesActivity.class);

  @Test
  public void writeToEditTextWorksAlsoWhenEditTextIsRepeatedInMultipleViewPagerViews() {
    String input = "Hello, I'm a test!";
    writeToEditText(R.id.edittext, input);
    assertDisplayed(input);
  }
}
