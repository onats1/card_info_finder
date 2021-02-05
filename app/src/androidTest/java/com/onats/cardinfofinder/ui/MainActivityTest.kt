package com.onats.cardinfofinder.ui

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.onats.cardinfofinder.R
import com.onats.cardinfofinder.TestCardInfoApp
import com.onats.cardinfofinder.framework.MainActivity
import com.onats.cardinfofinder.util.EspressoIdlingResourceRule
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val idlingResourceRule = EspressoIdlingResourceRule()

    @Before
    fun before() {
        val app = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .applicationContext as TestCardInfoApp

        app.testAppComponent.inject(this)
    }

    @Test
    fun launchActivity_checkInitialViewStates() {


        launchActivity<MainActivity>()

        //Check that message is displayed to user
        onView(withText(R.string.user_prompt)).check(matches(isDisplayed()))
        //Check that searchview is displayed
        onView(withId(R.id.numberInputLayout)).check(matches(isDisplayed()))
        //Check that Card details result is not displayed
        onView(withId(R.id.card_details_container)).check(matches(not(isDisplayed())))

    }

    @Test
    fun testQuery() {

        launchActivity<MainActivity>()

        onView(withId(R.id.numberInput)).perform(typeText("539941"))
        onView(withId(R.id.searchButton)).perform(click())
        onView(withId(R.id.card_details_container)).check(matches(isDisplayed()))

    }

    @Test
    fun testResetView_whenSearchFieldIsCleared() {

        launchActivity<MainActivity>()
        onView(withId(R.id.numberInput)).perform(typeText("539941"))
        onView(withId(R.id.searchButton)).perform(click())
        onView(withId(R.id.card_details_container)).check(matches(isDisplayed()))
        onView(withId(R.id.numberInput)).perform(typeText(""))
        onView(withId(R.id.cardBrandValue)).check(matches(withText(containsString(""))))
        onView(withId(R.id.cardTypeValue)).check(matches(withText(containsString(""))))
        onView(withId(R.id.bankValue)).check(matches(withText(containsString(""))))
        onView(withId(R.id.countryValue)).check(matches(withText(containsString(""))))

    }

}