package com.schibsted.spain.barista.interaction

import androidx.annotation.IdRes
import androidx.test.espresso.contrib.DrawerActions.close
import androidx.test.espresso.contrib.DrawerActions.open
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.schibsted.spain.barista.internal.performAction

object BaristaDrawerInteractions {

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
    matcher.performAction(open(gravity))
  }

  @JvmStatic
  @JvmOverloads
  fun closeDrawerWithGravity(@IdRes drawerId: Int? = null, gravity: Int) {
    val matcher = findDrawerMatcher(drawerId)
    matcher.performAction(close(gravity))
  }

  private fun findDrawerMatcher(drawerLayoutId: Int?) = when (drawerLayoutId) {
    null -> isAssignableFrom(androidx.drawerlayout.widget.DrawerLayout::class.java)
    else -> withId(drawerLayoutId)
  }
}
