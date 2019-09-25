package com.schibsted.spain.barista.interaction

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector

private const val DIALOG_TIMEOUT = 1500L
private const val ALLOW_BUTTON_RESID = "permission_allow_button"

object PermissionGranter {
  @Suppress("UNUSED_PARAMETER") // kept here for backward compatibility
  @JvmStatic
  fun allowPermissionsIfNeeded(permissionNeeded: String) {
      if (userInputNeededForPermission()) {
        clickGrantPermissionButton()
      }
  }

  private fun userInputNeededForPermission() = SDK_INT >= M

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
          findObject(dialogButtonSelector(ALLOW_BUTTON_RESID))

  private fun dialogButtonSelector(resId: String) : UiSelector {
      val regex = ".*$resId"
      return UiSelector()
              .clickable(true)
              .checkable(false)
              .resourceIdMatches(regex)
  }

}