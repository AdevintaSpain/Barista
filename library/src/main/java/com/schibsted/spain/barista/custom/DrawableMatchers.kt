package com.schibsted.spain.barista.custom

object DrawableMatchers {

  @JvmStatic
  fun withDrawable(resourceId: Int) = DrawableMatcher(resourceId)
}