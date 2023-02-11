package com.breens.mvvmdictionaryapp.home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.breens.mvvmdictionaryapp.common.Resource
import com.breens.mvvmdictionaryapp.common.UiEvents
import com.breens.mvvmdictionaryapp.home.data.repository.DefinitionRepository
import com.breens.mvvmdictionaryapp.home.presentation.uistate.DefinitionUiState
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
    private val definitionRepository: DefinitionRepository
) : ViewModel() {

    private val _definitionUiState = MutableStateFlow(DefinitionUiState())
    val definitionUiState: StateFlow<DefinitionUiState> = _definitionUiState.asStateFlow()

    private val _typedWord = mutableStateOf("")
    val typedWord: State<String> = _typedWord
    fun setTypedWord(typedWord: String) {
        _typedWord.value = typedWord
    }

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    fun getDefinition() {
        _definitionUiState.value =
            definitionUiState.value.copy(
                isLoading = true
            )

        val word = typedWord.value

        if (word.isNotEmpty()) {
            viewModelScope.launch {
                definitionRepository.getDefinition(word = word).collect { response ->
                    when (response) {
                        is Resource.Error -> {
                            _definitionUiState.value = definitionUiState.value.copy(
                                isLoading = false,
                                definition = emptyList()
                            )

                            _eventFlow.emit(
                                UiEvents.SnackBarEvent(
                                    message = response.message ?: "Something went wrong!"
                                )
                            )
                        }
                        is Resource.Success -> {
                            _definitionUiState.value = definitionUiState.value.copy(
                                isLoading = false,
                                definition = response.data
                            )
                        }
                        else -> {
                            definitionUiState
                        }
                    }
                }
            }
        } else {
            showErrorMessage()
        }
    }

    private fun showErrorMessage() {
        viewModelScope.launch {
            _definitionUiState.value =
                definitionUiState.value.copy(
                    isLoading = false
                )

            _eventFlow.emit(
                UiEvents.SnackBarEvent(
                    message = "Please enter a word"
                )
            )
        }
    }
}