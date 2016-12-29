package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.action.ViewActions;
import android.widget.AdapterView;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.schibsted.spain.barista.BaristaClickActions.click;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class BaristaSpinnerActions {

    public static void chooseSpinnerPosition(@IdRes int id, Class<?> itemClass, int position) {
        click(id);

        onData(is(instanceOf(itemClass)))
                .inAdapterView(allOf(isAssignableFrom(AdapterView.class), isDisplayed()))
                .atPosition(position)
                .perform(ViewActions.click());
    }

    public static void chooseSpinnerPositions(@IdRes int id, Class<?> itemClass, List<Integer> positions) {
        click(id);
        for (int i : positions) {
            onData(is(instanceOf(itemClass))).inAdapterView(allOf(isAssignableFrom(AdapterView.class), isDisplayed())).atPosition(i).perform(ViewActions.click());
        }
    }
}
