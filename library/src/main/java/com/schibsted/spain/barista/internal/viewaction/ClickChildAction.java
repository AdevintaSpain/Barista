package com.schibsted.spain.barista.internal.viewaction;

import android.support.annotation.IdRes;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.util.HumanReadables;
import android.view.View;
import android.widget.TextView;
import com.schibsted.spain.barista.internal.failurehandler.HelperFunctionsKt;
import com.schibsted.spain.barista.internal.util.ViewTreeAnalyzer;
import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class ClickChildAction {

  public static ViewAction clickChildWithId(@IdRes final int childId) {

    final Matcher<View> childMatcher = withId(childId);

    return new ViewAction() {
      @Override
      public Matcher<View> getConstraints() {
        return allOf(isDisplayed(), hasDescendant(childMatcher));
      }

      @Override
      public String getDescription() {
        return "Click on a child view " + HelperFunctionsKt.description(childMatcher);
      }

      @Override
      public void perform(UiController uiController, View view) {
        View child = view.findViewById(childId);
        if (child != null) {
          child.performClick();
        } else {
          throw new PerformException.Builder()
              .withActionDescription(getDescription())
              .withViewDescription(HumanReadables.describe(view))
              .withCause(new RuntimeException("Didn't find any view " + HelperFunctionsKt.description(childMatcher)))
              .build();
        }
      }
    };
  }

  public static ViewAction clickChildWithText(final String text) {
    return new ViewAction() {
      @Override
      public Matcher<View> getConstraints() {
        return allOf(isDisplayed(), hasDescendant(withText(text)));
      }

      @Override
      public String getDescription() {
        return "Click on a child View with specified text";
      }

      @Override
      public void perform(UiController uiController, View view) {
        for (View child : ViewTreeAnalyzer.getAllChildren(view)) {
          if (child instanceof TextView) {
            TextView textView = (TextView) child;
            String label = textView.getText().toString();
            if (text.equalsIgnoreCase(label)) {
              textView.performClick();
              return;
            }
          }
        }
      }
    };
  }
}