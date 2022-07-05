package com.app.sportsevents.ui.error

import androidx.lifecycle.ViewModelProvider
import com.app.sportsevents.R
import com.app.sportsevents.databinding.FragmentErrorBinding
import com.app.sportsevents.ui.base.BaseSportsEventsFragment

class ErrorFragment : BaseSportsEventsFragment<ErrorViewModel, FragmentErrorBinding>() {

    override fun createViewModel() = ViewModelProvider(this)[ErrorViewModel::class.java]

    override fun getLayoutId() =  R.layout.fragment_error
}