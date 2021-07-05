package com.adevinta.android.barista.internal.viewaction

import android.view.View
import android.widget.RatingBar
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
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
