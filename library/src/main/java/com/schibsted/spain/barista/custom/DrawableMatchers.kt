package com.schibsted.spain.barista.custom

class DrawableMatchers {

  companion object {
    @JvmStatic
    fun withDrawable(resourceId: Int) = DrawableMatcher(resourceId)
  }
}