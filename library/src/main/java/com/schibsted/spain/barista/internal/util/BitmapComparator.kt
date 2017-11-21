package com.schibsted.spain.barista.internal.util

import android.graphics.Bitmap
import java.util.*

internal class BitmapComparator {

  companion object {
    fun compare(b1: Bitmap, b2: Bitmap): Boolean {
      if (b1.width == b2.width && b1.height == b2.height) {
        val pixels1 = IntArray(b1.width * b1.height)
        val pixels2 = IntArray(b2.width * b2.height)
        b1.getPixels(pixels1, 0, b1.width, 0, 0, b1.width, b1.height)
        b2.getPixels(pixels2, 0, b2.width, 0, 0, b2.width, b2.height)
        return Arrays.equals(pixels1, pixels2)
      } else {
        return false
      }
    }
  }
}