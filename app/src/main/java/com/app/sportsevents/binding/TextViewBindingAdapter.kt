package com.app.sportsevents.binding

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.sportsevents.utils.getDateObject
import java.time.Instant

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
    text?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val timeMillis = Instant.parse(text).toEpochMilli()
            val dateObject = getDateObject(timeMillis)
            textView.text = "${dateObject.dayOfWeek}, ${dateObject.dayOfTheMonth} ${dateObject.month} ${dateObject.year} - ${dateObject.time}"
        } else {
            textView.text = it
        }
    }
}