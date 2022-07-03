package com.app.sportsevents.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.sportsevents.common.SportEventCardClickListener
import com.app.sportsevents.network.entity.SportEvent
import com.app.sportsevents.repository.SportEventsRepository
import com.app.sportsevents.ui.base.BaseSportsEventsViewModel
import com.app.sportsevents.utils.getMocks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScheduleViewModel() : BaseSportsEventsViewModel(), SportEventCardClickListener {

    private val _schedule = MutableLiveData<List<SportEvent>>()
    val schedule: LiveData<List<SportEvent>> = _schedule

    fun onResume() {
        if (_schedule.value == null) getEvents()
    }

    private fun getEvents() {
        _schedule.value = getMocks()
        /*viewModelScope.launch {
            repository.getSchedule().let { response ->
                if (response.isSuccessful) {
                    _schedule.value = response.body()
                }
            }
        }*/
    }

    override fun onEventCardClick(sportEvent: SportEvent) {
    }

}