//package com.schibsted.spain.barista.assertion
//
//import android.content.res.Resources
//import android.support.annotation.IdRes
//import android.support.test.espresso.Espresso
//import android.support.test.espresso.assertion.ViewAssertions
//import android.support.test.espresso.contrib.RecyclerViewActions
//import android.support.test.espresso.matcher.ViewMatchers
//import android.support.v7.widget.RecyclerView
//import android.view.View
//import org.hamcrest.Description
//import org.hamcrest.Matcher
//import org.hamcrest.TypeSafeMatcher
//
//object BaristaRecyclerViewMatcher {
//
//    @JvmStatic
//    fun assertDisplayedAtPosition(@IdRes recyclerViewId: Int, position: Int, text: String) {
//        Espresso.onView(ViewMatchers.withId(recyclerViewId))
//                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
//
//        Espresso.onView(atPosition(recyclerViewId, position))
//                .check(ViewAssertions.matches(ViewMatchers.withText(text)))
//    }
//
//    @JvmStatic
//    fun assertDisplayedAtPosition(@IdRes recyclerViewId: Int, position: Int, viewId: Int, text: String) {
//        Espresso.onView(ViewMatchers.withId(recyclerViewId))
//                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
//
//        Espresso.onView(atPositionOnView(recyclerViewId, position, viewId))
//                .check(ViewAssertions.matches(ViewMatchers.withText(text)))
//
//    }
//
//    private fun atPosition(@IdRes recyclerViewId: Int, position: Int): Matcher<View> = atPositionOnView(recyclerViewId, position, -1)
//
//    private fun atPositionOnView(@IdRes recyclerViewId: Int, position: Int, @IdRes targetViewId: Int): Matcher<View> {
//        return object : TypeSafeMatcher<View>() {
//            var resources: Resources? = null
//            var childView: View? = null
//
//            override fun describeTo(description: Description) {
//                var idDescription = recyclerViewId.toString()
//
//                resources?.let {
//                    try {
//                        idDescription = it.getResourceName(recyclerViewId)
//                    } catch (e: Resources.NotFoundException) {
//                        idDescription = "$recyclerViewId (resource name not found)"
//                    }
//                }
//
//                description.appendText("RecyclerView with id $idDescription at position $position")
//            }
//
//            override fun matchesSafely(view: View): Boolean {
//                resources = view.resources
//
//                if (childView == null) {
//                    val recyclerView: RecyclerView? = view.rootView.findViewById(recyclerViewId) as RecyclerView
//                    if (recyclerView != null && recyclerView.id == recyclerViewId) {
//                        val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
//                        viewHolder?.let { checkedViewHolder ->
//                            childView = checkedViewHolder.itemView
//                        }
//                    } else {
//                        return false
//                    }
//                }
//
//                if (targetViewId == -1) {
//                    return view == childView
//                } else {
//                    val targetView = childView?.findViewById(targetViewId)
//                    return view == targetView
//                }
//            }
//        }
//    }
//}