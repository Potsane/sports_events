package com.app.sportsevents.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.sportsevents.BaseTestRule
import com.app.sportsevents.network.SportsEventsApiService
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SportEventsRepositoryTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = BaseTestRule()

    @Mock
    private lateinit var apiService: SportsEventsApiService

    private val repository by lazy { SportEventsRepository(apiService) }

    @Test
    fun `should make api call to fetch events`() = testCoroutineRule.runBlockingTest{
        repository.getEvents()
        verify(apiService).getEvents()
    }

    @Test
    fun `should make api call to fetch schedule`() = testCoroutineRule.runBlockingTest{
        repository.getSchedule()
        verify(apiService).getSchedule()
    }
}