package com.app.sportsevents.ui.schedule

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.sportsevents.BaseTestRule
import com.app.sportsevents.repository.SportEventsRepository
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ScheduleViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = BaseTestRule()

    @Mock
    private lateinit var repository: SportEventsRepository

    private val viewModel by lazy { ScheduleViewModel(repository) }

    @Test(expected = Exception::class)
    fun `should not initialize events in case of an error`() = testCoroutineRule.runBlockingTest{
        doThrow(Exception()).`when`(repository).getSchedule()
        viewModel.onResume()
        Assert.assertNull(viewModel.schedule.value)
    }

    @Test(expected = Exception::class)
    fun `should get list of events`() = testCoroutineRule.runBlockingTest {
        doThrow(Exception()).`when`(repository).getSchedule()
        viewModel.onResume()
        verify(repository).getSchedule()
    }
}