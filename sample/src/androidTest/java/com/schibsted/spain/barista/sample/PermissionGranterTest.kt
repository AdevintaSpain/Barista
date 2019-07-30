package com.schibsted.spain.barista.sample

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.PermissionGranter
import com.schibsted.spain.barista.internal.failurehandler.BaristaException
import com.schibsted.spain.barista.rule.BaristaRule
import org.junit.Assume.assumeFalse
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class PermissionGranterTest {

    @get:Rule
    var thrown = ExpectedException.none()

    @get:Rule
    var activityRule = BaristaRule.create(RuntimePermissionActivity::class.java)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        assumeTrue("This test needs to run on a device with Android 23 or above",
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        assumeFalse("This test expects you to not have the permission granted. Remember to clear data.",
                hasNeededPermission(getApplicationContext(), RuntimePermissionActivity.SOME_PERMISSION))
    }

    @Test(expected = BaristaException::class)
    fun fails_when_using_permission() {
        activityRule.launchActivity()

        clickOn(R.id.use_permission_button)
    }

    @Test
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    fun works_after_granting_permission() {
        activityRule.launchActivity()

        clickOn(R.id.request_permission_button)

        PermissionGranter.allowPermissionsIfNeeded(Manifest.permission.READ_CONTACTS)

        clickOn(R.id.use_permission_button)
    }

    private fun hasNeededPermission(context: Context, permissionNeeded: String): Boolean {
        val permissionStatus = context.checkPermission(permissionNeeded, android.os.Process.myPid(), android.os.Process.myUid())
        return permissionStatus == PackageManager.PERMISSION_GRANTED
    }
}
