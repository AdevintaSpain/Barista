package com.schibsted.spain.barista.assertion

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoActivityResumedException
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.schibsted.spain.barista.internal.failurehandler.RethrowingFailureHandler
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedWithId
import com.schibsted.spain.barista.internal.matcher.DrawableMatchers.withDrawable
import com.schibsted.spain.barista.internal.matcher.RecyclerViewItemCountAssertion
import org.junit.Assert.fail

object BaristaAssertions {

    @JvmStatic
    fun assertThatBackButtonClosesTheApp() {
        // Will launch an Exception if it closes the app
        try {
            onView(isRoot())
                    .withFailureHandler(RethrowingFailureHandler())
                    .perform(ViewActions.pressBack())
            // One of our Activities is appearing on the screen :(
            fail("The back button didn't close the app")
        } catch (noActivityException: NoActivityResumedException) {
            //Yey!
        }
    }

    @JvmStatic
    fun assertRecyclerViewItemCount(@IdRes recyclerViewId: Int, expectedItemCount: Int) {
        onView(displayedWithId(recyclerViewId)).check(
                RecyclerViewItemCountAssertion(expectedItemCount))
    }

    @JvmStatic
    fun assertDrawable(@IdRes id: Int, @DrawableRes drawable: Int) {
        onView(withId(id)).check(matches(withDrawable(drawable)))
    }
}
