package com.app.sportsevents.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.app.sportsevents.extension.toSingleEvent
import com.app.sportsevents.navigation.NavigationCommand

open class BaseSportsEventsViewModel : ViewModel() {

    private val _uiCommands = MutableLiveData<Any>().toSingleEvent() as MutableLiveData<Any>
    val uiCommands: LiveData<Any> = _uiCommands

    private val _navigationCommands = MutableLiveData<NavigationCommand>().toSingleEvent()
            as MutableLiveData<NavigationCommand>
    val navigationCommands: LiveData<NavigationCommand> = _navigationCommands

    protected fun postUiCommand(command: Any) {
        _uiCommands.postValue(command)
    }

    protected fun goBack() {
        _navigationCommands.postValue(NavigationCommand.Back)
    }

    protected fun navigate(directions: NavDirections) {
        _navigationCommands.postValue(NavigationCommand.ToDirection(directions))
    }
}