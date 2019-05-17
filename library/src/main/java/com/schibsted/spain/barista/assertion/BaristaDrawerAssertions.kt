package com.schibsted.spain.barista.assertion

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.matcher.ViewMatchers
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

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
    null -> ViewMatchers.isAssignableFrom(androidx.drawerlayout.widget.DrawerLayout::class.java)
    else -> ViewMatchers.withId(drawerLayoutId)
  }
}