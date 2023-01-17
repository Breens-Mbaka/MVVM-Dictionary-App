package com.breens.mvvmdictionaryapp.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.breens.mvvmdictionaryapp.R
import com.breens.mvvmdictionaryapp.common.UiEvents
import com.breens.mvvmdictionaryapp.home.data.remote.MeaningPresentationModel
import com.breens.mvvmdictionaryapp.home.presentation.components.EmptyComponent
import com.breens.mvvmdictionaryapp.home.presentation.components.LoadingComponent
import com.breens.mvvmdictionaryapp.home.presentation.components.SearchTextFieldComponent
import com.breens.mvvmdictionaryapp.home.presentation.uistate.DefinitionUiState
import com.breens.mvvmdictionaryapp.home.presentation.uistate.SearchWordUiState
import com.breens.mvvmdictionaryapp.ui.theme.MVVMDictionaryAppTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    definitionViewModel: DefinitionViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    val definitionUiState = definitionViewModel.definitionUiState.collectAsState().value

    LaunchedEffect(key1 = true) {
        definitionViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackBarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    val searchWordUiState = definitionViewModel.searchWordUiState.collectAsState().value

    val definitions =
        if (definitionUiState.definition?.isNotEmpty() == true) definitionUiState.definition[0].meanings
            ?: emptyList()
        else emptyList()

    MVVMDictionaryAppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.nunitosans_bold)),
                                        color = Color.White
                                    )
                                ) {
                                    append("Your\n")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                                        color = Color.White
                                    )
                                ) {
                                    append("Dictionary")
                                }
                            }
                        )
                    },
                    backgroundColor = Color(0xFF4C7AF2)
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(contentPadding = PaddingValues(14.dp)) {
                    item {
                        TextFieldAndRecentWordsComponent(
                            searchWordUiState = searchWordUiState,
                            definitionViewModel = definitionViewModel
                        )
                    }
                    if (definitionUiState.isLoading || definitionUiState.definition?.isEmpty() == true) {
                        item {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                LoadingComponent(
                                    definitionUiState = definitionUiState
                                )

                                EmptyComponent(
                                    definitionUiState = definitionUiState
                                )
                            }
                        }
                    }
                    if (!definitionUiState.isLoading && !definitionUiState.definition.isNullOrEmpty()) {
                        item {
                            Spacer(
                                modifier = Modifier.height(15.dp)
                            )

                            PronunciationComponent(
                                definitionUiState = definitionUiState
                            )
                        }
                        items(definitions) { meaning ->
                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )

                            DefinitionsComponent(
                                meaning = meaning
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TextFieldAndRecentWordsComponent(
    searchWordUiState: SearchWordUiState,
    definitionViewModel: DefinitionViewModel
) {
    SearchTextFieldComponent(
        searchWordUiState = searchWordUiState,
        setWordToBeSearched = { word ->
            definitionViewModel.setWordToBeSearched(word)
        },
        searchWord = {
            val word = searchWordUiState.word
            if (!word.isNullOrEmpty()) {
                definitionViewModel.getDefinition(
                    word = word
                )
            }
        },
        showErrorMessage = { errorMessage ->
            definitionViewModel.showErrorMessage(errorMessage)
        }
    )
}

@Composable
fun PronunciationComponent(
    definitionUiState: DefinitionUiState
) {
    val word = remember(key1 = definitionUiState) {
        definitionUiState.definition?.get(0)?.word
    }
    val phonetic = remember(key1 = definitionUiState) {
        definitionUiState.definition?.get(0)?.phonetic
    }

    Column {
        Text(
            text = word ?: "-",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.nunitosans_extrabold))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = phonetic ?: "-",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.nunitosans_bold)),
                color = Color(0xFF4C7AF2)
            )
        }
    }
}
@Composable
fun DefinitionsComponent(meaning: MeaningPresentationModel) {
    Column {
        HeaderComponent(
            headerText = meaning.partOfSpeech ?: "",
            size = meaning.definitions?.size ?: 0,
            color = Color(0XFF4C7AF2)
        )

        meaning.definitions?.forEachIndexed { index, meaning ->
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = FontFamily(Font(R.font.nunitosans_semibold)),
                            color = Color.Black
                        )
                    ) {
                        append("${index + 1}. ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontFamily = FontFamily(Font(R.font.nunitosans_regular))
                        )
                    ) {
                        append(meaning.definition ?: "")
                    }
                }
            )
        }
    }
}

@Composable
fun HeaderComponent(headerText: String, size: Int, color: Color) {
    Button(
        onClick = {},
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.nunitosans_bold)),
                        color = Color.White
                    )
                ) {
                    append("$headerText ")
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily(Font(R.font.nunitosans_bold)),
                        color = Color.White
                    )
                ) {
                    append("($size)")
                }
            }
        )
    }
}