package com.schibsted.spain.barista.internal.failurehandler

import android.view.View
import org.hamcrest.Matcher

data class EspressoFailure(val throwable: Throwable, val matcher: Matcher<View>)