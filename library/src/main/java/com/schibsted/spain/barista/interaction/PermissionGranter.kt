package com.schibsted.spain.barista.interaction

import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import android.os.Build.VERSION_CODES.Q
import android.util.Log
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector

private const val DIALOG_TIMEOUT = 3000L
private val PERMISSIONS_DIALOG_ALLOW_ID = if (SDK_INT >= Q) {
  "com.android.permissioncontroller:id/permission_allow_button"
} else {
  "com.android.packageinstaller:id/permission_allow_button"
}

object PermissionGranter {
  @JvmStatic
  fun allowPermissionsIfNeeded(permissionNeeded: String) {
    try {
      if (dialogRequestNeededForPermission(permissionNeeded)) {
        clickGrantPermissionButton()
      }
    } catch (e: UiObjectNotFoundException) {
      Log.e("Barista", "There is no permissions dialog to interact with", e)
    }
  }

  private fun dialogRequestNeededForPermission(permissionNeeded: String) =
          SDK_INT >= M && !hasNeededPermission(getApplicationContext(), permissionNeeded)

  private fun clickGrantPermissionButton() {
    val device = createDeviceInstance()
    val grantPermissionButton = device.findGrantPermissionButton()
    grantPermissionButton.safeClick()
  }

  private fun createDeviceInstance() : UiDevice =
          UiDevice.getInstance(getInstrumentation())

  private fun UiObject.safeClick() {
    waitForExists(DIALOG_TIMEOUT)
    click()
    waitUntilGone(DIALOG_TIMEOUT)
  }

  private fun UiDevice.findGrantPermissionButton() : UiObject =
          findObject(dialogButtonSelector(PERMISSIONS_DIALOG_ALLOW_ID))

  private fun dialogButtonSelector(resId: String) : UiSelector =
          UiSelector()
            .clickable(true)
            .checkable(false)
            .resourceId(resId)

  private fun hasNeededPermission(context: Context, permissionNeeded: String): Boolean {
    val permissionStatus = checkSelfPermission(context, permissionNeeded)
    return permissionStatus == PERMISSION_GRANTED
  }

  private fun checkSelfPermission(context: Context, permission: String): Int {
    return context.checkPermission(permission, android.os.Process.myPid(), android.os.Process.myUid())
  }

}