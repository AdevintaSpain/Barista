package com.schibsted.spain.barista.internal.util.permissiongranter

import android.content.pm.PackageManager.PERMISSION_GRANTED
import com.schibsted.spain.barista.interaction.PermissionGranter.allowPermissionsIfNeeded
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeFalse
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

    private fun hasPermission(permission: String) =
            activity.checkSelfPermission(permission) == PERMISSION_GRANTED
}