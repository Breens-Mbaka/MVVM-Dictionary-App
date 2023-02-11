package com.breens.mvvmdictionaryapp

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.breens.mvvmdictionaryapp.home.presentation.HomeContent
import com.breens.mvvmdictionaryapp.home.presentation.uistate.DefinitionUiState
import com.breens.mvvmdictionaryapp.home.ui.theme.MVVMDictionaryAppTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun show_searchTextField_initially() {
        composeTestRule.setContent {
            MVVMDictionaryAppTheme {
                HomeContent(
                    definitionUiState = DefinitionUiState(),
                    typedWord = "",
                    setWordToBeSearched = {},
                    searchWord = {},
                    meanings = emptyList()
                )
            }
        }

        composeTestRule
            .onNodeWithText("Search here")
            .assertIsDisplayed()
    }

    @Test
    fun when_loadingDefinition_showLoadingTextComponent_and_loadingAnimation() {
        composeTestRule.setContent {
            MVVMDictionaryAppTheme {
                HomeContent(
                    definitionUiState = DefinitionUiState(
                        isLoading = true
                    ),
                    typedWord = "",
                    setWordToBeSearched = {},
                    searchWord = {},
                    meanings = emptyList()
                )
            }
        }

        composeTestRule
            .onNodeWithText("Please wait while we the definition...")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("Loading Animation")
            .assertIsDisplayed()
    }

    @Test
    fun when_isNotLoadingDefinition_and_definitionIsEmpty_showEmptyTextComponent_and_emptyAnimation() {
        composeTestRule.setContent {
            MVVMDictionaryAppTheme {
                HomeContent(
                    definitionUiState = DefinitionUiState(
                        isLoading = false,
                        definition = emptyList()
                    ),
                    typedWord = "",
                    setWordToBeSearched = {},
                    searchWord = {},
                    meanings = emptyList()
                )
            }
        }

        composeTestRule
            .onNodeWithText("Sorry the definition wasn't found...")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("Empty Animation")
            .assertIsDisplayed()
    }

    @Test
    fun when_isNotLoadingDefinition_and_definitionIsNotEmpty_showPronunciationComponent_and_partsOfSpeechDefinitionComponent() {
        composeTestRule.setContent {
            MVVMDictionaryAppTheme {
                HomeContent(
                    definitionUiState = DefinitionUiState(
                        isLoading = false,
                        definition = definitionsTestData
                    ),
                    typedWord = "",
                    setWordToBeSearched = {},
                    searchWord = {},
                    meanings = meaningsTestData
                )
            }
        }

        composeTestRule
            .onNodeWithText("hello")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("həˈləʊ")
            .assertIsDisplayed()

        composeTestRule
            .onAllNodesWithContentDescription("PartsOfSpeech")
            .assertCountEquals(3)
            .onFirst()
            .assertTextEquals("exclamation (1)")

        composeTestRule
            .onAllNodesWithContentDescription("definition")
            .assertCountEquals(3)
            .onLast()
            .assertTextEquals("1. say or shout ‘hello’.")
    }

    @Test
    fun when_searchTextFieldIsClicked_isFocused() {
        composeTestRule.setContent {
            MVVMDictionaryAppTheme {
                HomeContent(
                    definitionUiState = DefinitionUiState(),
                    typedWord = "",
                    setWordToBeSearched = {},
                    searchWord = {},
                    meanings = emptyList()
                )
            }
        }

        composeTestRule
            .onNodeWithText("Search here")
            .performClick()
            .assertIsFocused()
    }

    @Test
    fun when_searchTextField_isNotEmpty_clearIcon_isVisible() {
        composeTestRule.setContent {
            MVVMDictionaryAppTheme {
                HomeContent(
                    definitionUiState = DefinitionUiState(),
                    typedWord = "Soccer",
                    setWordToBeSearched = {},
                    searchWord = {},
                    meanings = emptyList()
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Clear Icon")
            .assertIsDisplayed()
    }


    @Test
    fun when_clearIcon_isClicked_searchTextField_isNotFocused() {
        composeTestRule.setContent {
            MVVMDictionaryAppTheme {
                HomeContent(
                    definitionUiState = DefinitionUiState(),
                    typedWord = "Soccer",
                    setWordToBeSearched = {},
                    searchWord = {},
                    meanings = emptyList()
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Clear Icon")
            .performClick()


        composeTestRule
            .onNodeWithContentDescription("SearchTextField")
            .assertIsNotFocused()
    }
}