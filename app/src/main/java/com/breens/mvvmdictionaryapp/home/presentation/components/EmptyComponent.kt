package com.breens.mvvmdictionaryapp.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.breens.mvvmdictionaryapp.R
import com.breens.mvvmdictionaryapp.home.presentation.uistate.DefinitionUiState

@Composable
fun EmptyComponent(definitionUiState: DefinitionUiState) {
    if (!definitionUiState.isLoading && definitionUiState.definition.isNullOrEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                EmptyAnimation()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Sorry the definition wasn't found...",
                    fontFamily = FontFamily(Font(R.font.nunitosans_light)),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun EmptyAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))
    LottieAnimation(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        iterations = LottieConstants.IterateForever,
        composition = composition,
    )
}