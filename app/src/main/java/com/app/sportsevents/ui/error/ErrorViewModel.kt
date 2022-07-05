package com.app.sportsevents.ui.error

import com.app.sportsevents.ui.base.BaseSportsEventsViewModel

class ErrorViewModel : BaseSportsEventsViewModel(){

    fun retry() = goBack()

    fun goHome() = navigate(ErrorFragmentDirections.toHome())
}