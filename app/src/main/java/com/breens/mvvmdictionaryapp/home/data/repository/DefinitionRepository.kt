package com.breens.mvvmdictionaryapp.home.data.repository

import com.breens.mvvmdictionaryapp.common.Resource
import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionPresentationModelItem
import kotlinx.coroutines.flow.Flow

interface DefinitionRepository {
    suspend fun getDefinition(word: String): Flow<Resource<List<DefinitionPresentationModelItem>>>
}