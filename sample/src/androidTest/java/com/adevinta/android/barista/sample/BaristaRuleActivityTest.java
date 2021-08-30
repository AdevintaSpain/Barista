package com.adevinta.android.barista.sample;

import com.adevinta.android.barista.rule.BaristaRule;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class BaristaRuleActivityTest {

  @Rule
  public BaristaRule<HelloWorldActivity> baristaRule = BaristaRule.create(HelloWorldActivity.class);

  @Test
  public void check_get_activity() {
    baristaRule.launchActivity();

    assertThat(baristaRule.getActivityTestRule().getActivity(), is(notNullValue()));
  }
}
