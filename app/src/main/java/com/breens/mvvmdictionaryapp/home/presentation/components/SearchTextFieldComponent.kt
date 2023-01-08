package com.breens.mvvmdictionaryapp.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.breens.mvvmdictionaryapp.home.presentation.uistate.SearchWordUiState
import com.breens.mvvmdictionaryapp.ui.theme.Shapes

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextFieldComponent(
    searchWordUiState: SearchWordUiState,
    setWordToBeSearched: (String) -> Unit,
    searchWord: () -> Unit
) {
    val word = remember(key1 = searchWordUiState) {
        searchWordUiState.word
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val focusManager = LocalFocusManager.current

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
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search",
                modifier = Modifier.clickable {
                    searchWord()
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            )
        },
        trailingIcon = {
            if (!word.isNullOrEmpty()) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "Search",
                    modifier = Modifier.clickable {
                        setWordToBeSearched("")
                    }
                )
            }
        },
        shape = Shapes.small,
        colors = TextFieldDefaults
            .textFieldColors(
                backgroundColor = Color(0xFFEBE7E7),
                unfocusedIndicatorColor = Color(0xFFEBE7E7),
                focusedIndicatorColor = Color(0xFF4C7AF2)
            ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        )
    )
}