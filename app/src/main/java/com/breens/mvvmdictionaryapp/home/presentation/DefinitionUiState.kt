package com.breens.mvvmdictionaryapp.home.presentation

import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionPresentationModelItem

data class DefinitionUiState(
    val definition: List<DefinitionPresentationModelItem>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
