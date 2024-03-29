package com.adevinta.android.barista.sample;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import org.junit.Rule;
import org.junit.Test;

import static com.adevinta.android.barista.assertion.BaristaImageViewAssertions.assertHasAnyDrawable;
import static com.adevinta.android.barista.assertion.BaristaImageViewAssertions.assertHasNoDrawable;
import static com.adevinta.android.barista.intents.BaristaIntents.mockAndroidCamera;
import static com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn;

public class CameraTest {

  @Rule
  public IntentsTestRule<CameraActivity> activityRule = new IntentsTestRule<>(CameraActivity.class);

  @Test
  public void imageViewIsEmptyBeforeTakingThePicture() {
    assertHasNoDrawable(R.id.image_view);
  }

  @Test
  public void takePictureAndShowIt() {
    mockAndroidCamera();

    clickOn(R.id.take_picture);

    assertHasAnyDrawable(R.id.image_view);
  }
}