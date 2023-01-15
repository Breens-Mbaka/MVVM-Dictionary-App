package com.breens.mvvmdictionaryapp.home.presentation.uistate

import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionPresentationModel
import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionPresentationModelItem

data class DefinitionUiState(
    val definition: List<DefinitionPresentationModelItem>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val canNavigate: Boolean = false
)

data class DefinitionTypeUiState(
    val definitionType: List<DefinitionPresentationModel>? = null
)