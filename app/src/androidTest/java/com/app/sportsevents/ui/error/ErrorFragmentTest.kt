package com.app.sportsevents.ui.error

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
@MediumTest
@HiltAndroidTest
class ErrorFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val navController = mock(NavController::class.java)

    @Before
    fun init() {
        hiltRule.inject()
        launchFragmentInHiltContainer<ErrorFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun clickingRetryButton_shouldGoBack() {
        onView(withId(R.id.button_try_again))
            .perform(click())
        verify(navController).navigateUp()
    }

    @Test
    fun screenDescription_shouldDisplayCorrectMessage() {
        onView(withId(R.id.text_error_message))
            .check(matches(withText(R.string.generic_error_message)))
    }
}