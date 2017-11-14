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

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.support.test.espresso.intent.Checks.checkNotNull;
import static org.hamcrest.Matchers.is;

public class BaristaIntentMatchers {

    public static Matcher<Intent> hasAction(final String action) {
        return hasAction(is(action));
    }

    public static Matcher<Intent> hasAction(final Matcher<String> actionMatcher) {
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

                        createBitmap(uri);
                    }
                    return true;
                }
                return false;
            }
        };
    }

    private static void createBitmap(Uri uri) {
        try {
            OutputStream stream = InstrumentationRegistry.getTargetContext().getContentResolver().openOutputStream(uri);
            if (stream != null) {
                Bitmap bmp = createBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

                byte[] bitmapData = byteArrayOutputStream.toByteArray();
                stream.write(bitmapData);
                stream.flush();
                stream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private static Bitmap createBitmap() {
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(100, 100, conf);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(10, 10, 90, 90, paint);
        canvas.save();
        return bmp;
    }
}
