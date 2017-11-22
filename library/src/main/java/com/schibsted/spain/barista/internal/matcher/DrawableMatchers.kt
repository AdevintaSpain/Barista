package com.schibsted.spain.barista.internal.matcher

object DrawableMatchers {

    @JvmStatic
    fun withDrawable(resourceId: Int) = DrawableMatcher(resourceId)

    @JvmStatic
    fun withAnyDrawable() = DrawableMatcher(DrawableMatcher.any())

    @JvmStatic
    fun withoutDrawable() = DrawableMatcher(DrawableMatcher.empty())
}