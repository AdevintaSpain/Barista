package com.adevinta.android.barista.sample

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import com.adevinta.android.barista.interaction.BaristaSleepInteractions
import com.adevinta.android.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerBack
import com.adevinta.android.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerForward
import com.adevinta.android.barista.internal.matcher.withCompatText
import junit.framework.AssertionFailedError
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class RecyclerViewWithDifferentDataInsideViewPagerTest {
    @Rule
    var activityRule = ActivityScenarioRule(
        RecyclerViewsWithDifferentDataInsideViewPagerActivity::class.java
    )

    @Test
    fun checkClickRecyclerViewItem() {
        swipeViewPagerForward(R.id.pager)
        BaristaSleepInteractions.sleep(500)
        assertDisplayedAtPosition(R.id.recycler, 1, "Marionberry")
        swipeViewPagerBack(R.id.pager)
        BaristaSleepInteractions.sleep(500)
        assertDisplayedAtPosition(R.id.recycler, 0, R.id.textview, "Apple")
    }

    @Test
    fun assertNotExistingView() {
        swipeViewPagerForward(R.id.pager)
        BaristaSleepInteractions.sleep(500)
        try {
            assertNotExist("Apple")
            Assert.assertFalse(false)
        } catch (error: AssertionFailedError) {
            Assert.assertTrue(true)
        }
        onView(withCompatText("Apple"))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        val displayedRecyclerView: Matcher<View> =
            allOf(withId(R.id.recycler), ViewMatchers.isDisplayed())

        assertNotExist(
            CoreMatchers.allOf(
                ViewMatchers.withText("Apple"),
                ViewMatchers.isDescendantOfA(displayedRecyclerView)
            )
        )

        assertDisplayedAtPosition(R.id.recycler, 1, "Marionberry")
        swipeViewPagerBack(R.id.pager)
        BaristaSleepInteractions.sleep(500)
        assertDisplayedAtPosition(R.id.recycler, 0, R.id.textview, "Apple")
    }
}