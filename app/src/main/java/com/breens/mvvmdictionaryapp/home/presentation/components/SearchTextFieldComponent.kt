package com.breens.mvvmdictionaryapp.home.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.breens.mvvmdictionaryapp.home.presentation.uistate.SearchWordUiState
import com.breens.mvvmdictionaryapp.ui.theme.Shapes

@Composable
fun SearchTextFieldComponent(
    searchWordUiState: SearchWordUiState,
    setWordToBeSearched: (String) -> Unit
) {
    val word = remember(key1 = searchWordUiState) {
        searchWordUiState.word
    }

    OutlinedTextField(
        value = word ?: "",
        onValueChange = { enteredWord ->
            setWordToBeSearched(enteredWord)
        },
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = {
            Text("Search here")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")
        },
        shape = Shapes.small,
        colors = TextFieldDefaults
            .textFieldColors(
                backgroundColor = Color(0xFFEBE7E7),
                unfocusedIndicatorColor = Color(0xFFEBE7E7)
            )
    )
}