package com.schibsted.spain.barista.internal.viewaction;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import org.hamcrest.Matcher;

import static android.support.test.espresso.action.ViewActions.actionWithAssertions;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static org.hamcrest.Matchers.allOf;

public class SwipeRefreshActions {

  public static ViewAction pullToRefresh() {
    return actionWithAssertions(new PullToRefresh());
  }

  public static class PullToRefresh implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
      return allOf(isDisplayingAtLeast(80), isAssignableFrom(SwipeRefreshLayout.class));
    }

    @Override
    public String getDescription() {
      return "Perform pull-to-refresh action";
    }

    @Override
    public void perform(UiController uiController, View view) {
      swipeDown().perform(uiController, view);
    }
  }
}
