package com.schibsted.spain.barista.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.schibsted.spain.barista.BaristaSwipeActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;

@RunWith(AndroidJUnit4.class)
public class ViewPagerTest {

    @Rule
    public ActivityTestRule<ViewPagerActivity> activityRule = new ActivityTestRule<>(ViewPagerActivity.class);

    @Test
    public void checkSwipeForward() {
        BaristaSwipeActions.swipePagerForward(R.id.viewPager);

        assertTextIsDisplayed("2");
    }

    @Test
    public void checkSwipeBack() {
        BaristaSwipeActions.swipePagerForward(R.id.viewPager);
        BaristaSwipeActions.swipePagerBack(R.id.viewPager);

        assertTextIsDisplayed("1");
    }

    @Test
    public void swipingBackInTheFirstPageDoesntCrash() {
        BaristaSwipeActions.swipePagerBack(R.id.viewPager);

        assertTextIsDisplayed("1");
    }

    @Test
    public void swipingForwardInTheLastPageDoesntCrash() {
        BaristaSwipeActions.swipePagerForward(R.id.viewPager);
        BaristaSwipeActions.swipePagerForward(R.id.viewPager);

        assertTextIsDisplayed("2");
    }
}
