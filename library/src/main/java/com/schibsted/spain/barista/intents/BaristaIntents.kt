package com.schibsted.spain.barista.intents

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle

import androidx.test.espresso.intent.Intents.intending
import com.schibsted.spain.barista.intents.BaristaIntentMatchers.captureImage

object BaristaIntents {

  private val DEFAULT_SIZE = 100

  @JvmStatic
  @JvmOverloads
  fun mockAndroidCamera(width: Int = DEFAULT_SIZE, height: Int = DEFAULT_SIZE) {
    val result = createImageCaptureStub()
    intending(captureImage(width, height)).respondWith(result)
  }

  @JvmStatic
  private fun createImageCaptureStub(): Instrumentation.ActivityResult {
    val resultBundle = Bundle()

    val resultData = Intent()
    resultData.putExtras(resultBundle)

    return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
  }
}
