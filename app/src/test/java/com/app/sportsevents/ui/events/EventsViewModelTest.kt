package com.app.sportsevents.ui.events

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.sportsevents.BaseTestRule
import com.app.sportsevents.network.entity.SportEvent
import com.app.sportsevents.repository.SportEventsRepository
import com.nhaarman.mockitokotlin2.doThrow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EventsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = BaseTestRule()

    @Mock
    private lateinit var repository: SportEventsRepository

    private val viewModel by lazy { EventsViewModel(repository) }

    @Test
    fun `should get list of events`() = testCoroutineRule.runBlockingTest {
        mockEventData()
        viewModel.onResume()
        val events = viewModel.events.value
        assertEquals(1, events?.size)
        assertEquals("1", events?.get(0)?.id)
        assertEquals("title", events?.get(0)?.title)
        assertEquals("image url", events?.get(0)?.imageUrl)
    }

    @Test(expected = Exception::class)
    fun `should not initialize events in case of an error`() = testCoroutineRule.runBlockingTest{
        doThrow(Exception()).`when`(repository).getEvents()
        viewModel.onResume()
        assertNull(viewModel.events.value)
    }

    private suspend fun mockEventData() {
        val events = listOf(
            SportEvent(
                id = "1",
                title = "title",
                subtitle = "sub title",
                date = "01-01-1970",
                imageUrl = "image url",
                videoUrl = "video url"
            )
        )
        doReturn(Response.success(events)).`when`(repository).getEvents()
    }
}