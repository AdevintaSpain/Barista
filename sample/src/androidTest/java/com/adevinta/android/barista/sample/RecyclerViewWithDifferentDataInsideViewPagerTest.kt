package com.adevinta.android.barista.sample

import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.adevinta.android.barista.interaction.BaristaSleepInteractions
import com.adevinta.android.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerBack
import com.adevinta.android.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerForward
import org.junit.Rule
import org.junit.Test

class RecyclerViewWithDifferentDataInsideViewPagerTest {
    @Rule
    var activityRule = ActivityTestRule(
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
}