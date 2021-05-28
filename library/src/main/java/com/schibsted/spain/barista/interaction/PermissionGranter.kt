package com.schibsted.spain.barista.interaction

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleepThread

object PermissionGranter {

  private val PERMISSIONS_DIALOG_DELAY = 3000

  private val PERMISSION_DIALOG_ALLOW_FOREGROUND_IDS = listOf(
    "com.android.permissioncontroller:id/permission_allow_foreground_only_button",
    "com.android.permissioncontroller:id/permission_allow_button",
    "com.android.packageinstaller:id/permission_allow_button"
  )

  private val PERMISSION_DIALOG_ALLOW_ONE_TIME_IDS = listOf(
    "com.android.permissioncontroller:id/permission_allow_one_time_button",
    "com.android.permissioncontroller:id/permission_allow_button",
    "com.android.packageinstaller:id/permission_allow_button"
  )

  // In API 30 the "Deny" button has the first ID when it is shown the first time. Second time
  // it has the second ID (do not ask again), although text is the same. In API 29 the buttons
  // are shown separately.
  private val PERMISSION_DIALOG_DENY_IDS = listOf(
    "com.android.permissioncontroller:id/permission_deny_button",
    "com.android.permissioncontroller:id/permission_deny_and_dont_ask_again_button",
    "com.android.packageinstaller:id/permission_deny_button"
  )

  private fun List<String>.toPermissionButtonRegex() = joinToString(
    prefix = "^(",
    separator = "|",
    postfix = ")$"
  ) { it }

  @JvmStatic
  fun allowPermissionsIfNeeded(permissionNeeded: String) {
    clickPermissionDialogButton(permissionNeeded, PERMISSION_DIALOG_ALLOW_FOREGROUND_IDS.toPermissionButtonRegex())
  }

  @JvmStatic
  fun allowPermissionOneTime(permissionNeeded: String) {
    clickPermissionDialogButton(permissionNeeded, PERMISSION_DIALOG_ALLOW_ONE_TIME_IDS.toPermissionButtonRegex())
  }

  @JvmStatic
  fun denyPermissions(permissionRequested: String) {
    clickPermissionDialogButton(permissionRequested, PERMISSION_DIALOG_DENY_IDS.toPermissionButtonRegex())
  }

  private fun clickPermissionDialogButton(permissionNeeded: String, permissionsIds: String) {
    try {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasNeededPermission(
          getApplicationContext(),
          permissionNeeded
        )) {
        sleepThread(PERMISSIONS_DIALOG_DELAY.toLong())
        val device = UiDevice.getInstance(getInstrumentation())

        val allowPermissions = device.findObject(
          UiSelector()
            .clickable(true)
            .checkable(false)
            .resourceIdMatches(permissionsIds)
        )
        if (allowPermissions.exists()) {
          allowPermissions.click()
        }
      }
    } catch (e: UiObjectNotFoundException) {
      Log.e("Barista", "There is no permissions dialog to interact with", e)
    }
  }

  private fun hasNeededPermission(context: Context, permissionNeeded: String): Boolean {
    val permissionStatus = checkSelfPermission(context, permissionNeeded)
    return permissionStatus == PackageManager.PERMISSION_GRANTED
  }

  private fun checkSelfPermission(context: Context, permission: String): Int {
    return context.checkPermission(permission, android.os.Process.myPid(), android.os.Process.myUid())
  }
}
