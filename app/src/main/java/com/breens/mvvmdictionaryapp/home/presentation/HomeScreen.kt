package com.breens.mvvmdictionaryapp.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breens.mvvmdictionaryapp.R
import com.breens.mvvmdictionaryapp.common.UiEvents
import com.breens.mvvmdictionaryapp.home.presentation.components.SearchTextFieldComponent
import com.breens.mvvmdictionaryapp.ui.theme.MVVMDictionaryAppTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(definitionViewModel: DefinitionViewModel) {
    val scaffoldState = rememberScaffoldState()

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
                                ) { // AnnotatedString.Builder
                                    append("Your\n")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                                        color = Color.White
                                    )
                                ) { // AnnotatedString.Builder
                                    append("Dictionary")
                                }
                            }
                        )
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Favorite Screen",
                                tint = Color.White
                            )
                        }
                    },
                    backgroundColor = Color(0xFF4C7AF2)
                )
            },
            backgroundColor = Color(0xFFF5F5F5)
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(contentPadding = PaddingValues(14.dp)) {
                    item {
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
                            }
                        )
                    }
                    item {
                    }
                }
            }
        }
    }
}