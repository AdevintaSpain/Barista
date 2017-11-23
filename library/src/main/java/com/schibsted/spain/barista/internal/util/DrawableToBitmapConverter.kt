package com.schibsted.spain.barista.internal.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable

class DrawableToBitmapConverter {
    fun getBitmap(drawable: Drawable?): Bitmap {
        val bitmap = Bitmap.createBitmap(drawable!!.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}