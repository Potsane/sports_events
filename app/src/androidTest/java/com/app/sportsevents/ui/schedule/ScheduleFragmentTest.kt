package com.app.sportsevents.ui.schedule

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
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
class ScheduleFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val navController = Mockito.mock(NavController::class.java)

    @Before
    fun init() {
        hiltRule.inject()
        launchFragmentInHiltContainer<ScheduleFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun displayCorrectScreenTitle() {
        Espresso.onView(ViewMatchers.withId(R.id.text_view_schedule_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.title_schedule)))
    }

    @Test
    fun showEventRecyclerView() {
        Espresso.onView(ViewMatchers.withId(R.id.schedule_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}