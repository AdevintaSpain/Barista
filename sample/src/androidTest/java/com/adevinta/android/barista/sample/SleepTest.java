package com.adevinta.android.barista.sample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.adevinta.android.barista.interaction.BaristaSleepInteractions;
import com.adevinta.android.barista.sample.util.FailureHandlerValidatorRule;
import java.util.concurrent.TimeUnit;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;


public class SleepTest {

  @Rule
  public ActivityTestRule<FlowFirstScreen> activityRule = new ActivityTestRule<>(FlowFirstScreen.class);

  @Rule
  public FailureHandlerValidatorRule handlerValidator = new FailureHandlerValidatorRule();

  @Test
  public void sleepForASecondInMillis() {
    long start = System.currentTimeMillis();
    BaristaSleepInteractions.sleep(1000);
    long end = System.currentTimeMillis();

    long duration = end - start;

    assertTrue(duration > 1000);
    assertTrue(duration < 2000);
  }

  @Test
  public void sleepForASecondInTimeUnits() {
    long start = System.currentTimeMillis();
    BaristaSleepInteractions.sleep(1, TimeUnit.SECONDS);
    long end = System.currentTimeMillis();

    long duration = end - start;

    assertTrue(duration > 1000);
    assertTrue(duration < 2000);
  }
}
