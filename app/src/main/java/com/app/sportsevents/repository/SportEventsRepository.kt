package com.app.sportsevents.repository

import com.app.sportsevents.network.SportsEventsApiService
import javax.inject.Inject

class SportEventsRepository @Inject constructor(private val apiService: SportsEventsApiService) {

    suspend fun getEvents() = apiService.getEvents()
    suspend fun getSchedule() = apiService.getSchedule()
}