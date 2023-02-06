package com.challenge.pixabay.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.Espresso
import com.challenge.pixabay.common.TestTags
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {

    }

    @Test
    fun pixabayApp_testSearchButton_expectedSearchAppBar() {
        composeTestRule.onNodeWithContentDescription("SearchFloatingActionButton").performClick()
        composeTestRule.onNodeWithContentDescription("SearchAppBarSearchIcon").assertIsDisplayed()
    }


    /*
    The purpose of this test if to check if the app is behaving perfectly in performing searches
    A fake repository is used for getting images
     */
    @Test
    fun pixabayApp_searchTheTermThree_expectTheePhotosToShowFromFakeRepository() {
        // wait until all photos shown
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag(TestTags.ImageCard)
                .fetchSemanticsNodes().size == 6
        }

        // click search button, enter "three" and perform search
        composeTestRule.onNodeWithTag(TestTags.SearchFloatingActionButton).performClick()
        composeTestRule.onNodeWithTag(TestTags.SearchAppBarText).performTextInput("three")
        Espresso.closeSoftKeyboard()
        composeTestRule.onNodeWithTag(TestTags.SearchAppBarSearch).performClick()

        // wait for result cards to show
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag(TestTags.ImageCard)
                .fetchSemanticsNodes().size == 3
        }

        // check three cards shown
        composeTestRule.onAllNodesWithTag(TestTags.ImageCard).assertCountEquals(3)

    }
}