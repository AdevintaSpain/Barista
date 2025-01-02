package com.adevinta.android.barista.interaction

import androidx.test.core.app.takeScreenshot
import androidx.test.core.graphics.writeToTestStorage
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.captureToBitmap
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import com.adevinta.android.barista.internal.performAction
import com.adevinta.android.barista.internal.util.resourceMatcher
import com.adevinta.android.barista.internal.matcher.withCompatText

object BaristaCaptureInteractions {
    @JvmStatic
    fun captureView(resId: Int, fileName: String) {
        resId.resourceMatcher().performAction(captureToBitmap { it.writeToTestStorage(fileName) })
    }

    @JvmStatic
    fun captureView(text: String, fileName: String) {
        withCompatText(text).performAction(captureToBitmap { it.writeToTestStorage(fileName) })
    }

    @JvmStatic
    fun captureActivity(fileName: String) {
        onView(isRoot()).perform(captureToBitmap { it.writeToTestStorage(fileName) })
    }

    @JvmStatic
    fun captureScreenshot(fileName: String) {
        takeScreenshot().writeToTestStorage(fileName)
    }
}
