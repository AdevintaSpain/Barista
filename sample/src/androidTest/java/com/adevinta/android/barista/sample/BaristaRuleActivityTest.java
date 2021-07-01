package com.adevinta.android.barista.sample;

import androidx.test.runner.AndroidJUnit4;
import com.adevinta.android.barista.rule.BaristaRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
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
