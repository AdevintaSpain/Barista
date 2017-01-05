package com.schibsted.spain.barista;

import android.support.annotation.IdRes;
import android.support.test.espresso.action.ViewActions;
import android.widget.AdapterView;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.schibsted.spain.barista.BaristaClickActions.click;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class BaristaSpinnerActions {

    public static void clickSpinnerItem(@IdRes int id, Class<?> modelClass, int... positions) {
        click(id);

        for (int p : positions) {
            performClick(p, modelClass);
        }
    }

    private static void performClick(int position, Class<?> modelClass) {
        onData(is(instanceOf(modelClass)))
                .inAdapterView(allOf(isAssignableFrom(AdapterView.class), isDisplayed()))
                .atPosition(position)
                .perform(ViewActions.click());
    }
}
