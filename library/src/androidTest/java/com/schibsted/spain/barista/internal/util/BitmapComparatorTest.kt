package com.schibsted.spain.barista.internal.util

import android.graphics.BitmapFactory
import androidx.test.InstrumentationRegistry
import com.schibsted.spain.barista.test.R
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BitmapComparatorTest {

  @Test
  fun returnTrueWhenComparingTheSameDrawable() {
    val aBitmap = BitmapFactory.decodeResource(InstrumentationRegistry.getTargetContext().resources, R.drawable.ic_barista)
    val theSameBitmap = BitmapFactory.decodeResource(InstrumentationRegistry.getTargetContext().resources, R.drawable.ic_barista)

    val result = BitmapComparator.compare(aBitmap, theSameBitmap)

    assertTrue(result)
  }

  @Test
  fun returnFalseWhenComparingDifferentDrawables() {
    val aBitmap = BitmapFactory.decodeResource(InstrumentationRegistry.getTargetContext().resources, R.drawable.ic_barista)
    val aDifferentBitmap = BitmapFactory.decodeResource(InstrumentationRegistry.getTargetContext().resources, R.drawable.ic_launcher)

    val result = BitmapComparator.compare(aBitmap, aDifferentBitmap)

    assertFalse(result)
  }
}