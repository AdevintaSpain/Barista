package com.schibsted.spain.barista.internal.util.permissiongranter

import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector
import com.schibsted.spain.barista.interaction.PermissionGranter.allowPermissionsIfNeeded
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeFalse
import org.junit.Assume.assumeTrue
import org.junit.Test

class PermissionGranterTest : TestFixtures() {
    private val uiTimeout = 500L
    private val grantPermissionButtonSelector
    get() = UiSelector()
            .clickable(true)
            .checkable(false)
            .resourceIdMatches(".*permission_allow_button")

    @Test
    fun grantOneNeededPermission() {
        assumeFalse(hasPermission(notGrantedA))

        requestPermission(notGrantedA)
        assumeTrue(isPermissionButtonDisplayed())

        allowPermissionsIfNeeded(notGrantedA)

        assertTrue(isPermissionButtonGone())
        assertTrue(hasPermission(notGrantedA))
    }

    @Test
    fun requestOneAlreadyGranted() {
        assumeTrue(hasPermission(grantedA))

        requestPermission(grantedA)
        assumeTrue(isPermissionButtonDisplayed())

        allowPermissionsIfNeeded(grantedA)

        assertTrue(isPermissionButtonGone())
        assertTrue(hasPermission(grantedA))
    }

    // Cannot request a permission not requested in the manifest
    @Test(expected = UiObjectNotFoundException::class)
    fun requestOneNotInManifest() {
        val granted = hasPermission(notInManifest)
        assumeFalse(granted)

        requestPermission(notInManifest)
        assumeFalse(isPermissionButtonDisplayed())

        allowPermissionsIfNeeded(notInManifest)

        assertTrue(isPermissionButtonGone())
        assertFalse(hasPermission(notInManifest))
    }

    private fun hasPermission(permission: String) =
            activity.checkSelfPermission(permission) == PERMISSION_GRANTED

    private fun requestPermission(vararg permission: String) =
            activity.requestPermissions(permission, requestCode)

    private fun isPermissionButtonDisplayed(): Boolean {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        return device.findObject(grantPermissionButtonSelector).waitForExists(uiTimeout)
    }

    private fun isPermissionButtonGone() : Boolean {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        return device.findObject(grantPermissionButtonSelector).waitUntilGone(uiTimeout)
    }


}