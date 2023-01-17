package com.breens.mvvmdictionaryapp.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breens.mvvmdictionaryapp.R

@Composable
fun PronunciationComponent(
    word: String,
    phonetic: String
) {
    Column {
        Text(
            text = word,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.nunitosans_extrabold))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = phonetic,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.nunitosans_bold)),
                color = Color(0xFF4C7AF2)
            )
        }
    }
}