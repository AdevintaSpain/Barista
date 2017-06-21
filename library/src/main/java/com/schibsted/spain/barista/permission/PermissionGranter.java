package com.schibsted.spain.barista.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static com.schibsted.spain.barista.BaristaSleepActions.sleepThread;

@RequiresApi(18)
public class PermissionGranter {

  private static final int PERMISSIONS_DIALOG_DELAY = 3000;
  private static final int GRANT_BUTTON_INDEX = 1;

  public static void allowPermissionsIfNeeded(String permissionNeeded) {
    try {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasNeededPermission(InstrumentationRegistry.getTargetContext(),
          permissionNeeded)) {
        sleepThread(PERMISSIONS_DIALOG_DELAY);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        UiObject allowPermissions = device.findObject(new UiSelector()
            .clickable(true)
            .checkable(false)
            .index(GRANT_BUTTON_INDEX));
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