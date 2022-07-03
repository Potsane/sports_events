package com.app.sportsevents.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.app.sportsevents.common.SportEventCardClickListener
import com.app.sportsevents.network.entity.SportEvent
import com.app.sportsevents.repository.SportEventsRepository
import com.app.sportsevents.ui.base.BaseSportsEventsViewModel
import com.app.sportsevents.utils.getMocks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventsViewModel() : BaseSportsEventsViewModel(), SportEventCardClickListener {

    private val _events = MutableLiveData<List<SportEvent>>()
    val events: LiveData<List<SportEvent>> = _events

    fun onResume() {
        if (_events.value == null) {
            getEvents()
        }

    }

    private fun getEvents() {
        _events.postValue(getMocks())
        /*viewModelScope.launch {
            repository.getEvents().let { response ->
                if (response.isSuccessful) {
                    _events.value = response.body()
                }
            }
        }*/
    }

    override fun onEventCardClick(sportEvent: SportEvent) {
    }
}