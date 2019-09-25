package com.schibsted.spain.barista.internal.util.permissiongranter

import android.Manifest.permission.*
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.schibsted.spain.barista.internal.util.MockActivity
import org.junit.Rule
import org.junit.Test

abstract class TestFixtures {
    protected val grantedA = ACCESS_FINE_LOCATION
    protected val grantedB = CAMERA
    protected val notGrantedA = READ_CONTACTS
    protected val notGrantedB = CALL_PHONE
    protected val notInManifest = ACCESS_WIFI_STATE

    protected val requestCode = 100

    @get:Rule
    val activityRule = ActivityTestRule<MockActivity>(MockActivity::class.java)

    protected val activity: MockActivity
        get() = activityRule.activity

    @get:Rule
    val grantPermissions: GrantPermissionRule = GrantPermissionRule.grant(grantedA, grantedB)
}