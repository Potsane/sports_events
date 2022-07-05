package com.app.sportsevents.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("showOrHide")
fun showOrHide(view: View, text: String?) {
    if (!text.isNullOrEmpty()) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}