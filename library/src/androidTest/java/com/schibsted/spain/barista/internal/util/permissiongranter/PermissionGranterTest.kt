package com.schibsted.spain.barista.internal.util.permissiongranter

import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.test.uiautomator.UiObjectNotFoundException
import com.schibsted.spain.barista.interaction.PermissionGranter.allowPermissionsIfNeeded
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeFalse
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test

class PermissionGranterTest: TestFixtures() {

    @Test
    fun grantOneNeededPermission() {
        var granted = hasPermission(notGrantedA)
        assumeFalse(granted)

        activity.requestPermissions(arrayOf(notGrantedA), requestCode)
        allowPermissionsIfNeeded(notGrantedA)

        granted = hasPermission(notGrantedA)
        assertTrue(granted)
    }

    @Test
    fun requestOneAlreadyGranted() {
        var granted = hasPermission(grantedA)
        assumeTrue(granted)

        activity.requestPermissions(arrayOf(grantedA), requestCode)
        allowPermissionsIfNeeded(grantedA)

        granted = hasPermission(grantedA)
        assertTrue(granted)
    }

    // Cannot request a permission not requested in the manifest
    @Test(expected = UiObjectNotFoundException::class)
    fun requestOneNotInManifest() {
        val granted = hasPermission(notInManifest)
        assumeFalse(granted)

        activity.requestPermissions(arrayOf(notInManifest), requestCode)
        allowPermissionsIfNeeded(notInManifest)
    }


    private fun hasPermission(permission: String) =
            activity.checkSelfPermission(permission) == PERMISSION_GRANTED
}