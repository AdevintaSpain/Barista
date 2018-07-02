package com.schibsted.spain.barista.interaction

import android.support.annotation.IdRes
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.internal.performAction

object BaristaEditTextInteractions {

    @JvmStatic
    fun writeTo(@IdRes editTextId: Int, text: String) {
        withId(editTextId).performAction(replaceText(text))
    }
}
