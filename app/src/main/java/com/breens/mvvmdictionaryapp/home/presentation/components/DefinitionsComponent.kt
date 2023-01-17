package com.breens.mvvmdictionaryapp.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import com.breens.mvvmdictionaryapp.R
import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionPresentationModel

@Composable
fun DefinitionsComponent(
    partsOfSpeech: String,
    definitions: List<DefinitionPresentationModel>?
) {
    Column {
        PartsOfSpeechComponent(
            headerText = partsOfSpeech,
            size = definitions?.size ?: 0,
            color = Color(0XFF4C7AF2)
        )

        definitions?.forEachIndexed { index, meaning ->
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