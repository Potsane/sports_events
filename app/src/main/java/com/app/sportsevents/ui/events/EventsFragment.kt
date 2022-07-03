package com.app.sportsevents.ui.events

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.sportsevents.R
import com.app.sportsevents.common.SportEventCardListAdapter
import com.app.sportsevents.databinding.FragmentEventsBinding
import com.app.sportsevents.network.entity.SportEvent
import com.app.sportsevents.ui.base.BaseSportsEventsFragment
import com.app.sportsevents.utils.getMocks

class EventsFragment : BaseSportsEventsFragment<EventsViewModel, FragmentEventsBinding>() {

    private var adapter: SportEventCardListAdapter? = null

    override fun getLayoutId() = R.layout.fragment_events

    override fun createViewModel() = ViewModelProvider(this)[EventsViewModel::class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel.events.observe(viewLifecycleOwner, Observer(::updateAdapter))
        updateAdapter(getMocks())
    }

    private fun updateAdapter(items: List<SportEvent>) {
        adapter?.updateItems(items.toMutableList()) ?: run {
            adapter = SportEventCardListAdapter(items.toMutableList(), viewModel)
            binding.eventsView.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}