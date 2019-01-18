package com.schibsted.spain.barista.assertion

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerMatchers.isClosed
import android.support.test.espresso.contrib.DrawerMatchers.isOpen
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout

object BaristaDrawerAssertions {

  private const val DEFAULT_GRAVITY = GravityCompat.START

  @JvmStatic
  @JvmOverloads
  fun assertDrawerIsOpen(@IdRes drawerId: Int? = null) {
    assertDrawerIsOpenWithGravity(drawerId, DEFAULT_GRAVITY)
  }

  @JvmStatic
  @JvmOverloads
  fun assertDrawerIsClosed(@IdRes drawerId: Int? = null) {
    assertDrawerIsClosedWithGravity(drawerId, DEFAULT_GRAVITY)
  }

  @JvmStatic
  @JvmOverloads
  fun assertDrawerIsOpenWithGravity(@IdRes drawerId: Int? = null, gravity: Int) {
    val matcher = findDrawerMatcher(drawerId)
    onView(matcher).check(matches(isOpen(gravity)))
  }

  @JvmStatic
  @JvmOverloads
  fun assertDrawerIsClosedWithGravity(@IdRes drawerId: Int? = null, gravity: Int) {
    val matcher = findDrawerMatcher(drawerId)
    onView(matcher).check(matches(isClosed(gravity)))
  }

  private fun findDrawerMatcher(drawerLayoutId: Int?) = when (drawerLayoutId) {
    null -> ViewMatchers.isAssignableFrom(DrawerLayout::class.java)
    else -> ViewMatchers.withId(drawerLayoutId)
  }
}