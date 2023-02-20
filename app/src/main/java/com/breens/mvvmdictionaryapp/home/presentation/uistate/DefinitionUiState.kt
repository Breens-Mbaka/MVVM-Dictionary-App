package com.breens.mvvmdictionaryapp.home.presentation.uistate

import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionResponseModel

data class DefinitionUiState(
    val definition: List<DefinitionResponseModel>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)