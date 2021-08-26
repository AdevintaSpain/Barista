package com.adevinta.android.barista.sample

import android.content.Context
import android.graphics.BitmapFactory
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert
import org.junit.Test

class BitmapComparatorTest {

  @Test
  fun returnTrueWhenComparingTheSameDrawable() {
    val aBitmap = BitmapFactory.decodeResource(ApplicationProvider.getApplicationContext<Context>().resources, R.drawable.ic_barista)
    val theSameBitmap = BitmapFactory.decodeResource(ApplicationProvider.getApplicationContext<Context>().resources, R.drawable.ic_barista)

    val result = aBitmap.sameAs(theSameBitmap)

    Assert.assertTrue(result)
  }

  @Test
  fun returnFalseWhenComparingDifferentDrawables() {
    val aBitmap = BitmapFactory.decodeResource(ApplicationProvider.getApplicationContext<Context>().resources, R.drawable.ic_barista)
    val aDifferentBitmap =
      BitmapFactory.decodeResource(ApplicationProvider.getApplicationContext<Context>().resources, R.drawable.ic_action_menu)

    val result = aBitmap.sameAs(aDifferentBitmap)

    Assert.assertFalse(result)
  }
}