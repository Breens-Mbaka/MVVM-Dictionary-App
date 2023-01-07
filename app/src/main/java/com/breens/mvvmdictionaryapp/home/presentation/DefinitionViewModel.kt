package com.breens.mvvmdictionaryapp.home.presentation

import androidx.lifecycle.ViewModel
import com.breens.mvvmdictionaryapp.home.data.repository.DefinitionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DefinitionViewModel @Inject constructor(
    private val definitionRepository: DefinitionRepository
):ViewModel() {

}