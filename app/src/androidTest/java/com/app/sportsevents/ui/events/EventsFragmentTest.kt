package com.app.sportsevents.ui.events

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.app.sportsevents.R
import com.app.sportsevents.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
@MediumTest
@HiltAndroidTest
class EventsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val navController = Mockito.mock(NavController::class.java)

    @Before
    fun init() {
        hiltRule.inject()
        launchFragmentInHiltContainer<EventsFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun displayCorrectScreenTitle() {
        onView(ViewMatchers.withId(R.id.text_view_events_title))
            .check(matches(ViewMatchers.withText(R.string.title_events)))
    }

    @Test
    fun showEventRecyclerView() {
        onView(ViewMatchers.withId(R.id.events_view))
            .check(matches(isDisplayed()))
    }
}