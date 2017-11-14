package com.schibsted.spain.barista.sample;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.BaristaAssertions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaClickActions.click;
import static com.schibsted.spain.barista.intents.BaristaIntents.captureIntent;

@RunWith(AndroidJUnit4.class)
public class CameraTest {

  @Rule
  public IntentsTestRule<CameraActivity> activityRule = new IntentsTestRule<>(CameraActivity.class);

  @Test
  public void takePictureAndShowIt() {
    captureIntent();

    click(R.id.take_picture);

    BaristaAssertions.assertDrawable(R.id.image_view);
  }
}