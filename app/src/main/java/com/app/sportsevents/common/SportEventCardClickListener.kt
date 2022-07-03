package com.app.sportsevents.common

import com.app.sportsevents.network.entity.SportEvent

interface SportEventCardClickListener {
    fun onEventCardClick(sportEvent: SportEvent)
}