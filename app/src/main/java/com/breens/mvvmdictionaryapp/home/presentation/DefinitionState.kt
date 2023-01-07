package com.breens.mvvmdictionaryapp.home.presentation

import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionApiModel

data class DefinitionState(
    val definition: DefinitionApiModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
