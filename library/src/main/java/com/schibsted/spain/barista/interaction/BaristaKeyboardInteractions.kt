package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.pressImeActionButton
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.interaction.BaristaScrollInteractions.safelyScrollTo
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedAnd

object BaristaKeyboardInteractions {

    @JvmStatic
    fun closeKeyboard() {
        onView(isRoot()).perform(closeSoftKeyboard())
    }

    @JvmStatic
    fun pressImeActionButton(@IdRes editTextId: Int) {
        safelyScrollTo(editTextId)
        onView(displayedAnd(withId(editTextId))).perform(pressImeActionButton())
    }
}
