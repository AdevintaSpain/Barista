package com.schibsted.spain.barista.internal.viewaction

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.view.View
import android.widget.RatingBar
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

object RatingBarActions {
    const val DESCRIPTION = "Set RatingBar progress."

    fun setRatingBarRatingTo(rating: Float): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(RatingBar::class.java))
            }

            override fun getDescription(): String {
                return DESCRIPTION
            }

            override fun perform(uiController: UiController, view: View) {
                val ratingBar = view as RatingBar
                ratingBar.rating = rating
            }
        }
    }

    fun setRatingBarRatingToMin(): ViewAction = setRatingBarRatingTo(0f)

    fun setRatingBarRatingToMax(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(RatingBar::class.java))
            }

            override fun getDescription(): String {
                return DESCRIPTION
            }

            override fun perform(uiController: UiController, view: View) {
                val ratingBar = view as RatingBar
                ratingBar.rating = ratingBar.max.toFloat()
            }
        }
    }
}
