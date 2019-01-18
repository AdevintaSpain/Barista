package com.schibsted.spain.barista.internal.util;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewTreeAnalyzer {
  public static List<View> getAllChildren(View parent) {
    if (!(parent instanceof ViewGroup)) {
      return Collections.singletonList(parent);
    }

    ArrayList<View> result = new ArrayList<>();
    ViewGroup viewGroup = (ViewGroup) parent;
    for (int i = 0; i < viewGroup.getChildCount(); i++) {
      View child = viewGroup.getChildAt(i);
      result.addAll(getAllChildren(child));
    }
    return result;
  }
}
