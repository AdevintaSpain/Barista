package com.schibsted.spain.barista.internal.matcher

object DrawableMatchers {

    @JvmStatic
    fun withDrawable(resourceId: Int) = DrawableMatcher(resourceId)
}