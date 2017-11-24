package com.schibsted.spain.barista.assertion

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoActivityResumedException
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import com.schibsted.spain.barista.internal.failurehandler.RethrowingFailureHandler
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

}
