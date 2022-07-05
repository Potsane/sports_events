package com.app.sportsevents.binding

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.sportsevents.utils.getFormattedDate

@BindingAdapter("showOrHide")
fun showOrHide(view: TextView, text: String?) {
    if (!text.isNullOrEmpty()) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("formatDate")
fun formatDate(textView: TextView, text: String?) {
    text?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textView.text = getFormattedDate(it)
        } else {
            textView.text = it
        }
    }
}