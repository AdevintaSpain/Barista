package com.schibsted.spain.barista.assertion

import android.content.res.Resources
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import android.view.View
import com.schibsted.spain.barista.internal.matcher.DisplayedMatchers.displayedWithId
import com.schibsted.spain.barista.internal.matcher.RecyclerViewItemCountAssertion
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object BaristaRecyclerViewAssertions {

  @JvmStatic
  fun assertRecyclerViewItemCount(@IdRes recyclerViewId: Int, expectedItemCount: Int) {
    onView(displayedWithId(recyclerViewId)).check(RecyclerViewItemCountAssertion(expectedItemCount))
  }

  @JvmStatic
  fun assertDisplayedAtPosition(@IdRes recyclerViewId: Int, position: Int, text: String) {
    onView(withId(recyclerViewId))
        .perform(scrollToPosition<RecyclerView.ViewHolder>(position))

    onView(atPosition(recyclerViewId, position))
        .check(matches(CoreMatchers.anyOf(withChild(withText(text)), withText(text))))

  }

  @JvmStatic
  fun assertDisplayedAtPosition(@IdRes recyclerViewId: Int, position: Int, viewId: Int, text: String) {
    onView(withId(recyclerViewId))
        .perform(scrollToPosition<RecyclerView.ViewHolder>(position))

    onView(atPositionOnView(recyclerViewId, position, viewId))
        .check(matches(withText(text)))
  }

  /**
   * Fork of the RecyclerViewMatcher from https://gist.github.com/baconpat/8405a88d04bd1942eb5e430d33e4faa2
   */
  private fun atPosition(@IdRes recyclerViewId: Int, position: Int): Matcher<View> = atPositionOnView(recyclerViewId, position, -1)

  private fun atPositionOnView(@IdRes recyclerViewId: Int, position: Int, @IdRes targetViewId: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
      var resources: Resources? = null
      var childView: View? = null

      override fun describeTo(description: Description) {
        var idDescription = recyclerViewId.toString()

        resources?.let {
          try {
            idDescription = it.getResourceName(recyclerViewId)
          } catch (e: Resources.NotFoundException) {
            idDescription = "$recyclerViewId (resource name not found)"
          }
        }

        description.appendText("RecyclerView with id $idDescription at position $position")
      }

      override fun matchesSafely(view: View): Boolean {
        resources = view.resources

        if (childView == null) {
          val recyclerView: RecyclerView? = view.rootView.findViewById(recyclerViewId) as RecyclerView
          if (recyclerView != null && recyclerView.id == recyclerViewId) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
            viewHolder?.let { checkedViewHolder ->
              childView = checkedViewHolder.itemView
            }
          } else {
            return false
          }
        }

        if (targetViewId == -1) {
          return view == childView
        } else {
          val targetView: View? = childView?.findViewById(targetViewId)
          return view == targetView
        }
      }
    }
  }
}