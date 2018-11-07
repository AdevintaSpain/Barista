package com.schibsted.spain.barista.interaction;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.test.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import android.util.Log;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleepThread;

public class PermissionGranter {

  private static final int PERMISSIONS_DIALOG_DELAY = 3000;
  private static final String PERMISSIONS_DIALOG_ALLOW_ID = "com.android.packageinstaller:id/permission_allow_button";
//    private static final String PERMISSIONS_DIALOG_DENY_ID = "com.android.packageinstaller:id/permission_deny_button";

  public static void allowPermissionsIfNeeded(String permissionNeeded) {
    try {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasNeededPermission(InstrumentationRegistry.getTargetContext(),
          permissionNeeded)) {
        sleepThread(PERMISSIONS_DIALOG_DELAY);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        UiObject allowPermissions = device.findObject(new UiSelector()
            .clickable(true)
            .checkable(false)
            .resourceId(PERMISSIONS_DIALOG_ALLOW_ID));
        if (allowPermissions.exists()) {
          allowPermissions.click();
        }
      }
    } catch (UiObjectNotFoundException e) {
      Log.e("Barista", "There is no permissions dialog to interact with", e);
    }
  }

  private static boolean hasNeededPermission(Context context, String permissionNeeded) {
    int permissionStatus = checkSelfPermission(context, permissionNeeded);
    return permissionStatus == PackageManager.PERMISSION_GRANTED;
  }

  private static int checkSelfPermission(@NonNull Context context, @NonNull String permission) {
    if (permission == null) {
      throw new IllegalArgumentException("permission is null");
    }
    return context.checkPermission(permission, android.os.Process.myPid(), android.os.Process.myUid());
  }
}