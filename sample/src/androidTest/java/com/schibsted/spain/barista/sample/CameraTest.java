package com.schibsted.spain.barista.sample;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.BaristaAssertions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.intent.Intents.intending;
import static com.schibsted.spain.barista.BaristaClickActions.click;
import static com.schibsted.spain.barista.intents.BaristaIntentMatchers.captureImage;

@RunWith(AndroidJUnit4.class)
public class CameraTest {

  @Rule
  public IntentsTestRule<CameraActivity> activityRule = new IntentsTestRule<>(CameraActivity.class);

  @Test
  public void takePictureAndShowIt() {
    Instrumentation.ActivityResult result = createImageCaptureStub();
    intending(captureImage(100, 100)).respondWith(result);

    click(R.id.take_picture);

    BaristaAssertions.assertDrawable(R.id.image_view);
  }

  private Instrumentation.ActivityResult createImageCaptureStub() {
    Bundle resultBundle = new Bundle();

    Intent resultData = new Intent();
    resultData.putExtras(resultBundle);

    return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
  }
}