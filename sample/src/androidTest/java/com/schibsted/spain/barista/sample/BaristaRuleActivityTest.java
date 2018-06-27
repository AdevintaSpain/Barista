package com.schibsted.spain.barista.sample;

import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.rule.BaristaRule;
import org.hamcrest.core.IsNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class BaristaRuleActivityTest {

  @Rule
  public BaristaRule<HelloWorldActivity> baristaRule = BaristaRule.create(HelloWorldActivity.class);

  @Test
  public void check_get_activity() {
    baristaRule.launchActivity();

    assertThat(baristaRule.getActivityTestRule().getActivity(), is(notNullValue()));
  }
}
