package com.schibsted.spain.barista.custom;

import android.support.annotation.IdRes;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.TextView;

import com.schibsted.spain.barista.util.ViewTreeAnalyzer;

import org.hamcrest.Matcher;

public class ClickChildAction {

  public static ViewAction clickChildWithId(@IdRes final int id) {
    return new ViewAction() {
      @Override
      public Matcher<View> getConstraints() {
        return null;
      }

      @Override
      public String getDescription() {
        return "Click on a child View with specified id";
      }

      @Override
      public void perform(UiController uiController, View view) {
        View child = view.findViewById(id);
        child.performClick();
      }
    };
  }

  public static ViewAction clickChildWithText(final String text) {
    return new ViewAction() {
      @Override
      public Matcher<View> getConstraints() {
        return null;
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