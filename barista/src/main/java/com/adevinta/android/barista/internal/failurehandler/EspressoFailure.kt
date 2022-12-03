package com.adevinta.android.barista.internal.failurehandler

import android.view.View
import org.hamcrest.Matcher

data class EspressoFailure(val throwable: Throwable, val matcher: Matcher<View>)