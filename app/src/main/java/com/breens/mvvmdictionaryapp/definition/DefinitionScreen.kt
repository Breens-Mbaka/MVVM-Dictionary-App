package com.breens.mvvmdictionaryapp.definition

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breens.mvvmdictionaryapp.R
import com.breens.mvvmdictionaryapp.ui.theme.MVVMDictionaryAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(showSystemUi = true)
fun DefinitionScreen() {
    val scaffoldState = rememberScaffoldState()

    val tabItems = listOf("Exclamation", "Noun", "Verb")

    val pagerState = rememberPagerState()

    MVVMDictionaryAppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Box(Modifier.fillMaxWidth()) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.align(Alignment.CenterStart)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.White
                                )
                            }

                            Text(
                                text = "Definition",
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.nunitosans_bold)),
                                color = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.Favorite,
                                contentDescription = "Favorite Screen",
                                tint = Color.White
                            )
                        }
                    },
                    backgroundColor = Color(0xFF4C7AF2)
                )
            }
        ) {
            Box(modifier = Modifier.padding(it).fillMaxSize()) {
                LazyColumn(contentPadding = PaddingValues(14.dp)) {
                    item {
                        PronunciationComponent()
                    }
                }
            }
        }
    }
}

@Composable
fun PronunciationComponent() {
    Column {
        Text(
            text = "Happy",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.nunitosans_extrabold))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "/həˈləʊ/ ",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.nunitosans_bold)),
                color = Color(0xFF4C7AF2)
            )
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.VolumeUp,
                    contentDescription = "Favorite Screen",
                    tint = Color(0xFF4C7AF2)
                )
            }
        }
    }
}
