package com.app.sportsevents.repository

import com.app.sportsevents.network.SportsEventsApiService
import com.app.sportsevents.network.entity.SportEvent
import retrofit2.Response
import javax.inject.Inject

class SportEventsRepository @Inject constructor(private val apiService: SportsEventsApiService) {

    suspend fun getEvents() = apiService.getEvents()
    suspend fun getSchedule(): Response<List<SportEvent>> {
        println("------ call made")
        return apiService.getSchedule()
    }
}