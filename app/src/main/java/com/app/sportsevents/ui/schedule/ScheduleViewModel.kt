package com.app.sportsevents.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.sportsevents.common.SportEventCardClickListener
import com.app.sportsevents.network.entity.SportEvent
import com.app.sportsevents.repository.SportEventsRepository
import com.app.sportsevents.ui.base.BaseSportsEventsViewModel
import com.app.sportsevents.ui.base.ShowProgress
import com.app.sportsevents.utils.THIRTY_SECONDS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: SportEventsRepository
) : BaseSportsEventsViewModel(), SportEventCardClickListener {

    var canRefresh = true
    var isFirstLoad = true
    private val _schedule = MutableLiveData<List<SportEvent>>()
    val schedule: LiveData<List<SportEvent>> = _schedule

    fun onResume() {
        isFirstLoad = true
        if (_schedule.value == null) getSchedule()
    }

    private fun getSchedule() {
        viewModelScope.launch {
            while (canRefresh) {
                postUiCommand(ShowProgress(isFirstLoad))
                try {
                    repository.getSchedule().let { response ->
                        if (response.isSuccessful) {
                            _schedule.value = response.body()
                        }
                    }
                    isFirstLoad = false
                    canRefresh = true
                    postUiCommand(ShowProgress(false))
                } catch (exception: Exception) {
                    canRefresh = false
                    navigate(ScheduleFragmentDirections.toError())
                }
                delay(THIRTY_SECONDS)
            }
        }
    }

    override fun onEventCardClick(sportEvent: SportEvent) {}
}