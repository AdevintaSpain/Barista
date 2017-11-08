package com.schibsted.spain.barista.custom;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ScrollToAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;

public class NestedEnabledScrollToAction implements ViewAction {
  private final ScrollToAction scrollToAction;

  public static NestedEnabledScrollToAction nestedScrollToAction() {
    return new NestedEnabledScrollToAction();
  }

  @Deprecated
  public static NestedEnabledScrollToAction scrollTo() {
    return nestedScrollToAction();
  }

  private NestedEnabledScrollToAction() {
    scrollToAction = new ScrollToAction();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Matcher<View> getConstraints() {
    return allOf(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE), isDescendantOfA(anyOf(
        isAssignableFrom(ScrollView.class), isAssignableFrom(HorizontalScrollView.class), isAssignableFrom(NestedScrollView.class))));
  }

  @Override
  public void perform(UiController uiController, View view) {
    scrollToAction.perform(uiController, view);
  }

  @Override
  public String getDescription() {
    return scrollToAction.getDescription();
  }
}