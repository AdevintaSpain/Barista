package com.schibsted.spain.barista.assertion

import android.support.annotation.ColorRes
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.matcher.ViewMatchers.*
import com.schibsted.spain.barista.internal.assertAny
import com.schibsted.spain.barista.internal.matcher.TextColorMatcher
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

object BaristaVisibilityAssertions {

    private const val defaultTimeout = 5000L
    private const val interval = 50L

    @JvmStatic
    fun assertDisplayed(viewId: Int) {
        viewId.resourceMatcher().assertAny(isDisplayed())
    }

    @JvmStatic
    fun assertDisplayed(text: String) {
        withText(text).assertAny(isDisplayed())
    }

    @JvmStatic
    fun assertDisplayed(@IdRes viewId: Int, text: String) {
        viewId.resourceMatcher().assertAny(withText(text))
    }

    @JvmStatic
    fun assertDisplayedAfterWait(@IdRes viewId: Int, delay: Long = defaultTimeout) {
        doUntilDisplayed(viewId, delay)
    }

    @JvmStatic
    fun assertDisplayedAfterWait(text: String, delay: Long = defaultTimeout) {
        doUntilDisplayed(text, delay)
    }

    @JvmStatic
    fun assertDisplayedAfterWait(@IdRes viewId: Int, text: String, delay: Long = defaultTimeout) {
        doUntilDisplayed(viewId, text, delay)
    }


    @JvmStatic
    fun assertNotExist(resId: Int) {
        onView(resId.resourceMatcher()).check(doesNotExist())
    }

    @JvmStatic
    fun assertNotExist(text: String) {
        onView(withText(text)).check(doesNotExist())
    }

    @JvmStatic
    fun assertNotDisplayed(viewId: Int) {
        viewId.resourceMatcher().assertAny(not(isDisplayed()))
    }

    @JvmStatic
    fun assertNotDisplayed(text: String) {
        withText(text).assertAny(not(isDisplayed()))
    }

    @JvmStatic
    fun assertNotDisplayed(@IdRes viewId: Int, text: String) {
        viewId.resourceMatcher().assertAny(not(withText(text)))
    }

    @JvmStatic
    fun assertContains(text: String) {
        withText(containsString(text)).assertAny(isDisplayed())
    }

    @JvmStatic
    fun assertContains(@IdRes viewId: Int, text: String) {
        viewId.resourceMatcher().assertAny(withText(containsString(text)))
    }

    @JvmStatic
    fun assertNotContains(text: String) {
        onView(withText(containsString(text))).check(doesNotExist())
    }

    @JvmStatic
    fun assertNotContains(@IdRes resId: Int, text: String) {
        onView(allOf(withId(resId), withText(containsString(text)))).check(doesNotExist())
    }

    @JvmStatic
    fun assertTextColorIs(@IdRes viewId: Int, @ColorRes colorRes: Int) {
        viewId.resourceMatcher().assertAny(TextColorMatcher(colorRes))
    }

    @JvmStatic
    fun assertTextColorIsNot(@IdRes viewId: Int, @ColorRes colorRes: Int) {
        viewId.resourceMatcher().assertAny(not(TextColorMatcher(colorRes)))
    }

    private fun doUntilDisplayed(viewId: Int, timeout: Long = defaultTimeout) {
        val startTime = System.currentTimeMillis()
        while (true) {
            try {
                viewId.resourceMatcher().assertAny(isDisplayed())
                break
            } catch (e: Throwable) {
                if (System.currentTimeMillis() - startTime > timeout) {
                    throw e
                }
                Thread.sleep(interval)
            }
        }
    }

    private fun doUntilDisplayed(text: String, timeout: Long = defaultTimeout) {
        val startTime = System.currentTimeMillis()
        while (true) {
            try {
                withText(text).assertAny(isDisplayed())
                break
            } catch (e: Throwable) {
                if (System.currentTimeMillis() - startTime > timeout) {
                    throw e
                }
                Thread.sleep(interval)
            }
        }
    }

    private fun doUntilDisplayed(viewId: Int, text: String, timeout: Long = defaultTimeout) {
        val startTime = System.currentTimeMillis()
        while (true) {
            try {
                viewId.resourceMatcher().assertAny(withText(text))
                break
            } catch (e: Throwable) {
                if (System.currentTimeMillis() - startTime > timeout) {
                    throw e
                }
                Thread.sleep(interval)
            }
        }
    }


}
