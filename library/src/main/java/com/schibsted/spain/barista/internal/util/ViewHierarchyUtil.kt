@file:JvmName("ViewHierarchyUtil")

package com.schibsted.spain.barista.internal.util;

import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

private const val START_INDEX = 0

/**
 * Gets view hierarchy. This method can be used to find with which views, espresso will compare the matcher.
 * Iterative BFS is used to find the views.
 *
 * @param view
 * @return list of view's that are child of the view and the view itself
 */
internal fun getViewHierarchyOf(view: View): List<View> {
    val undiscovered = ArrayList<View>()
    undiscovered.add(view)
    val discovered = ArrayList<View>()
    while (!undiscovered.isEmpty()) {
        val childView = undiscovered.removeAt(START_INDEX)
        discovered.add(childView)
        if (childView !is ViewGroup) {
            continue
        }
        for (index in START_INDEX until childView.childCount) {
            undiscovered.add(childView.getChildAt(index))
        }
    }
    return discovered
}
