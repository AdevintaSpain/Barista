package com.adevinta.android.barista.intents

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.provider.MediaStore
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull
import com.adevinta.android.barista.internal.failurehandler.BaristaException
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.OutputStream

internal object BaristaIntentMatchers {

    private val DEFAULT_SIZE = 100

    @JvmStatic
    fun captureImage(width: Int = DEFAULT_SIZE, height: Int = DEFAULT_SIZE): Matcher<Intent> {
        return hasAction(`is`(MediaStore.ACTION_IMAGE_CAPTURE), width, height)
    }

    private fun hasAction(actionMatcher: Matcher<String>, width: Int, height: Int): Matcher<Intent> {
        checkNotNull(actionMatcher)

        return object : TypeSafeMatcher<Intent>() {
            override fun describeTo(description: Description) {
                description.appendText("has action: ")
                description.appendDescriptionOf(actionMatcher)
            }

            public override fun matchesSafely(intent: Intent): Boolean {
                if (actionMatcher.matches(intent.action)) {
                    val extras = intent.extras
                    if (extras != null) {
                        val uri = extras.getParcelable<Uri>(MediaStore.EXTRA_OUTPUT)

                        try {
                            generateBitmapOnGivenUri(uri, width, height)
                        } catch (e: IOException) {
                            throw BaristaException("Not able to create Bitmap at $uri", e)
                        }
                    }
                    return true
                }
                return false
            }
        }
    }

    @Throws(IOException::class)
    private fun generateBitmapOnGivenUri(uri: Uri?, width: Int, height: Int) {
        uri?.let {
            val context: Context = getApplicationContext()
            val stream = context.contentResolver.openOutputStream(it)
            if (stream != null) {
                val bmp = createBitmap(width, height)

                writeBitmap(stream, bmp)
            }
        }
    }

    private fun createBitmap(width: Int, height: Int): Bitmap {
        val conf = Bitmap.Config.ARGB_8888
        val bmp = Bitmap.createBitmap(width, height, conf)

        val canvas = Canvas(bmp)

        val paint = Paint()
        paint.color = Color.RED

        val widthPart = width / 10
        val heightPart = height / 10
        canvas.drawRect(widthPart.toFloat(), heightPart.toFloat(), (width - widthPart).toFloat(), (height - heightPart).toFloat(), paint)

        canvas.save()

        return bmp
    }

    @Throws(IOException::class)
    private fun writeBitmap(stream: OutputStream, bmp: Bitmap) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val bitmapData = byteArrayOutputStream.toByteArray()
        stream.write(bitmapData)
        stream.flush()
        stream.close()
    }
}
