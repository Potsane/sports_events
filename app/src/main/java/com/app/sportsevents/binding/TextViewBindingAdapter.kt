package com.app.sportsevents.binding

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.sportsevents.utils.getDateAndTime

@BindingAdapter("showOrHide")
fun showOrHide(view: TextView, text: String?) {
    if (!text.isNullOrEmpty()) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("formatDate")
fun formatDate(textView: TextView, text: String?) {
    text?.let { textView.text = getDateAndTime(it) }
}