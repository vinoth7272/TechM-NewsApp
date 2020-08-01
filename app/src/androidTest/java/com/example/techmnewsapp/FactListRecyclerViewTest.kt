package com.example.techmnewsapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.techmnewsapp.view.FactsListActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class FactListRecyclerViewTest {

    private val LAST_ITEM_ID: String = "10"

    @get:Rule
    val activityScenario: ActivityScenarioRule<FactsListActivity> =
        ActivityScenarioRule(FactsListActivity::class.java)

    @get:Rule
    val activityRule = ActivityTestRule(FactsListActivity::class.java)

    /**
     * Method to test UI - to check scroll scroll position in the recyclerview
     */
    @Test
    fun scrollToPosition() {
        getCount()?.let {
            Espresso.onView(withId(R.id.recyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(1))
        }
    }

    /**
     * Method to test the last Item in the list is not exit while initial load
     */
    @Test
    fun lastItem_NotDisplayed() {
        Espresso.onView(ViewMatchers.withText(LAST_ITEM_ID)).check(ViewAssertions.doesNotExist())
    }

    /**
     * Test method to test the row click
     */
    @Test
    fun row_Click() {
        // Click on one of the rows.
        Espresso.onView(withId(R.id.recyclerView)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                ViewActions.click()
            )
        )

    }

    /**
     * Method to get the totak count from recyclerView
     */
    private fun getCount(): Int? {
        val recyclerView: RecyclerView =
            activityRule.activity.findViewById(R.id.recyclerView) as RecyclerView
        return recyclerView.adapter?.itemCount
    }
}
