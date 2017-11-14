package com.schibsted.spain.barista.intents;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

import static android.support.test.espresso.intent.Intents.intending;
import static com.schibsted.spain.barista.intents.BaristaIntentMatchers.captureImage;

public class BaristaIntents {

  private static final int DEFAULT_SIZE = 100;

  public static void mockAndroidCamera() {
    mockAndroidCamera(DEFAULT_SIZE, DEFAULT_SIZE);
  }

  public static void mockAndroidCamera(int width, int height) {
    Instrumentation.ActivityResult result = createImageCaptureStub();
    intending(captureImage(width, height)).respondWith(result);
  }

  private static Instrumentation.ActivityResult createImageCaptureStub() {
    Bundle resultBundle = new Bundle();

    Intent resultData = new Intent();
    resultData.putExtras(resultBundle);

    return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
  }

}
