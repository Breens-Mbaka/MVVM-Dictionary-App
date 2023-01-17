package com.breens.mvvmdictionaryapp.home.presentation.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breens.mvvmdictionaryapp.R

@Composable
fun PartsOfSpeechComponent(
    headerText: String,
    size: Int,
    color: Color
) {
    Button(
        onClick = {},
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        modifier = Modifier.semantics { contentDescription = "PartsOfSpeech" }
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