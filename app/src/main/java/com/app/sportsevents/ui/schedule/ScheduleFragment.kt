package com.app.sportsevents.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.sportsevents.R
import com.app.sportsevents.common.SportEventCardListAdapter
import com.app.sportsevents.databinding.FragmentScheduleBinding
import com.app.sportsevents.network.entity.SportEvent
import com.app.sportsevents.ui.base.BaseSportsEventsFragment

class ScheduleFragment : BaseSportsEventsFragment<ScheduleViewModel, FragmentScheduleBinding>() {

    private var adapter: SportEventCardListAdapter? = null

    override fun createViewModel() = ViewModelProvider(this)[ScheduleViewModel::class.java]

    override fun getLayoutId() = R.layout.fragment_schedule

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.schedule.observe(viewLifecycleOwner, Observer(::updateAdapter))
    }

    private fun updateAdapter(items: List<SportEvent>) {
        adapter?.updateItems(items.toMutableList()) ?: run {
            adapter = SportEventCardListAdapter(items.toMutableList(), viewModel)
            binding.eventsView.adapter = adapter
        }
    }
}