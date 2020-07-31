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

    private val LAST_ITEM_ID: String = "item: 10"

    @get:Rule
    val activityScenario: ActivityScenarioRule<FactsListActivity> =
        ActivityScenarioRule(FactsListActivity::class.java)

    @get:Rule
    val activityRule = ActivityTestRule(FactsListActivity::class.java)

    @Test
    fun scrollToPosition_checkText() {
        getCount()?.let {
            Espresso.onView(withId(R.id.recyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(1))
        }
    }

    @Test
    fun lastItem_NotDisplayed() {
        Espresso.onView(ViewMatchers.withText(LAST_ITEM_ID)).check(ViewAssertions.doesNotExist())
    }

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


    private fun getCount(): Int? {
        val recyclerView: RecyclerView =
            activityRule.activity.findViewById(R.id.recyclerView) as RecyclerView
        return recyclerView.adapter?.itemCount
    }
}
