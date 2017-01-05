package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.BaristaSwipeActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;
import static com.schibsted.spain.barista.BaristaSwipeActions.*;

@RunWith(AndroidJUnit4.class)
public class ViewPagerTest {

    @Rule
    public ActivityTestRule<ViewPagerActivity> activityRule = new ActivityTestRule<>(ViewPagerActivity.class);

    @Test
    public void checkSwipeForward() {
        swipePagerForward(R.id.viewPager);

        assertTextIsDisplayed("2");
    }

    @Test
    public void checkSwipeBack() {
        swipePagerForward(R.id.viewPager);
        swipePagerBack(R.id.viewPager);

        assertTextIsDisplayed("1");
    }

    @Test
    public void swipingBackInTheFirstPageDoesntCrash() {
        swipePagerBack(R.id.viewPager);

        assertTextIsDisplayed("1");
    }

    @Test
    public void swipingForwardInTheLastPageDoesntCrash() {
        swipePagerForward(R.id.viewPager);
        swipePagerForward(R.id.viewPager);

        assertTextIsDisplayed("2");
    }
}
