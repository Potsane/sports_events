package com.app.sportsevents.network

import com.app.sportsevents.network.entity.SportEvent
import retrofit2.Response
import retrofit2.http.GET

interface SportsEventsApiService {

    @GET("/getEvents")
    suspend fun getEvents(): Response<List<SportEvent>>

    @GET("/getSchedule")
    suspend fun getSchedule(): Response<List<SportEvent>>
}