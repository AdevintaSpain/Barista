package com.schibsted.spain.barista.action

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.contrib.DrawerActions.close
import android.support.test.espresso.contrib.DrawerActions.open
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout

object BaristaDrawerActions {

    private const val DEFAULT_GRAVITY = GravityCompat.START

    @JvmStatic
    @JvmOverloads
    fun openDrawer(@IdRes drawerId: Int? = null) {
        openDrawerWithGravity(drawerId, DEFAULT_GRAVITY)
    }

    @JvmStatic
    @JvmOverloads
    fun closeDrawer(@IdRes drawerId: Int? = null) {
        closeDrawerWithGravity(drawerId, DEFAULT_GRAVITY)
    }

    @JvmStatic
    @JvmOverloads
    fun openDrawerWithGravity(@IdRes drawerId: Int? = null, gravity: Int) {
        val matcher = findDrawerMatcher(drawerId)
        onView(matcher).perform(open(gravity))
    }

    @JvmStatic
    @JvmOverloads
    fun closeDrawerWithGravity(@IdRes drawerId: Int? = null, gravity: Int) {
        val matcher = findDrawerMatcher(drawerId)
        onView(matcher).perform(close(gravity))
    }

    private fun findDrawerMatcher(drawerLayoutId: Int?) = when (drawerLayoutId) {
        null -> isAssignableFrom(DrawerLayout::class.java)
        else -> withId(drawerLayoutId)
    }
}
