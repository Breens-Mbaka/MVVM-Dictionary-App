package com.breens.mvvmdictionaryapp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.breens.mvvmdictionaryapp.common.Resource
import com.breens.mvvmdictionaryapp.common.UiEvents
import com.breens.mvvmdictionaryapp.home.data.repository.DefinitionRepositoryImpl
import com.breens.mvvmdictionaryapp.home.presentation.uistate.DefinitionUiState
import com.breens.mvvmdictionaryapp.home.presentation.uistate.SearchWordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DefinitionViewModel @Inject constructor(
    private val definitionRepositoryImpl: DefinitionRepositoryImpl
) : ViewModel() {

    private val _definitionUiState = MutableStateFlow(DefinitionUiState())
    val definitionUiState: StateFlow<DefinitionUiState> = _definitionUiState.asStateFlow()

    private val _searchWordUiState = MutableStateFlow(SearchWordUiState())
    val searchWordUiState: StateFlow<SearchWordUiState> = _searchWordUiState.asStateFlow()

    private val _canNavigateUiState = MutableStateFlow(CanNavigateUiState())
    val canNavigateUiState: StateFlow<CanNavigateUiState> = _canNavigateUiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    fun getDefinition(word: String) {
        _definitionUiState.value =
            definitionUiState.value.copy(
                isLoading = true
            )

        _canNavigateUiState.value =
            canNavigateUiState.value.copy(
                canNavigate = false
            )
        viewModelScope.launch {
            definitionRepositoryImpl.getDefinition(word = word).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _definitionUiState.value = definitionUiState.value.copy(
                            isLoading = false,
                            definition = response.data,
                            canNavigate = true
                        )

                        _canNavigateUiState.value =
                            canNavigateUiState.value.copy(
                                canNavigate = true
                            )
                    }
                    is Resource.Error -> {
                        _definitionUiState.value = definitionUiState.value.copy(
                            isLoading = false,
                            definition = emptyList(),
                            canNavigate = false
                        )

                        _eventFlow.emit(
                            UiEvents.SnackBarEvent(
                                message = response.message ?: "Something went wrong!"
                            )
                        )

                        _canNavigateUiState.value =
                            canNavigateUiState.value.copy(
                                canNavigate = false
                            )
                    }
                    else -> {
                        definitionUiState
                    }
                }
            }
        }
    }

    fun showErrorMessage(message: String) {
        viewModelScope.launch {
            _eventFlow.emit(
                UiEvents.SnackBarEvent(
                    message = message
                )
            )
        }
    }

    fun setWordToBeSearched(word: String) {
        _searchWordUiState.value =
            searchWordUiState.value.copy(
                word = word
            )
    }
}

data class CanNavigateUiState(
    val canNavigate: Boolean = false
)