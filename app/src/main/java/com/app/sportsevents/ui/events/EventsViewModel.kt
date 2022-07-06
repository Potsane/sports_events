package com.app.sportsevents.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.sportsevents.common.SportEventCardClickListener
import com.app.sportsevents.network.entity.SportEvent
import com.app.sportsevents.repository.SportEventsRepository
import com.app.sportsevents.ui.base.BaseSportsEventsViewModel
import com.app.sportsevents.ui.base.ShowProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val repository: SportEventsRepository
) : BaseSportsEventsViewModel(), SportEventCardClickListener {

    private val _events = MutableLiveData<List<SportEvent>>()
    val events: LiveData<List<SportEvent>> = _events

    fun onResume() {
        if (_events.value == null) {
            getEvents()
        }
    }

    private fun getEvents() {
        viewModelScope.launch {
            postUiCommand(ShowProgress(true))
            try {
                repository.getEvents().let { response ->
                    if (response.isSuccessful) {
                        _events.value = response.body()
                    }
                }
                postUiCommand(ShowProgress(false))
            } catch (exception: Exception) {
                navigate(EventsFragmentDirections.toError())
            }
        }
    }

    override fun onEventCardClick(sportEvent: SportEvent) {
        navigate(EventsFragmentDirections.toPlayBack(sportEvent.videoUrl))
    }
}