package com.schibsted.spain.barista.intents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

import com.schibsted.spain.barista.internal.failurehandler.BaristaException;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.is;

public class BaristaIntentMatchers {

  public static Matcher<Intent> captureImage() {
    return hasAction(is(MediaStore.ACTION_IMAGE_CAPTURE), 100, 100);
  }

  public static Matcher<Intent> captureImage(int width, int height) {
    return hasAction(is(MediaStore.ACTION_IMAGE_CAPTURE), width, height);
  }

  private static Matcher<Intent> hasAction(final Matcher<String> actionMatcher, final int width, final int height) {
    checkNotNull(actionMatcher);

    return new TypeSafeMatcher<Intent>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("has action: ");
        description.appendDescriptionOf(actionMatcher);
      }

      @Override
      public boolean matchesSafely(Intent intent) {
        if (actionMatcher.matches(intent.getAction())) {
          Bundle extras = intent.getExtras();
          if (extras != null) {
            Uri uri = extras.getParcelable(MediaStore.EXTRA_OUTPUT);

            try {
              generateBitmapOnGivenUri(uri, width, height);
            } catch (IOException e) {
              throw new BaristaException(e.getMessage(), e);
            }
          }
          return true;
        }
        return false;
      }
    };
  }

  private static void generateBitmapOnGivenUri(Uri uri, int width, int height) throws IOException {
    OutputStream stream = InstrumentationRegistry.getTargetContext().getContentResolver().openOutputStream(uri);
    if (stream != null) {
      Bitmap bmp = createBitmap(width, height);

      writeBitmap(stream, bmp);
    }
  }

  @NonNull
  private static Bitmap createBitmap(int width, int height) {
    Bitmap.Config conf = Bitmap.Config.ARGB_8888;
    Bitmap bmp = Bitmap.createBitmap(width, height, conf);

    Canvas canvas = new Canvas(bmp);

    Paint paint = new Paint();
    paint.setColor(Color.RED);

    int widthPart = width / 10;
    int heightPart = height / 10;
    canvas.drawRect(widthPart, heightPart, width - widthPart, height - heightPart, paint);

    canvas.save();

    return bmp;
  }

  private static void writeBitmap(OutputStream stream, Bitmap bmp) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
    byte[] bitmapData = byteArrayOutputStream.toByteArray();
    stream.write(bitmapData);
    stream.flush();
    stream.close();
  }
}
