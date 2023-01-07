package com.breens.mvvmdictionaryapp.home.data.repository

import com.breens.mvvmdictionaryapp.common.Resource
import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionPresentationModelItem
import com.breens.mvvmdictionaryapp.home.data.remote.DictionaryRemoteDataSourceImpl
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

interface  DefinitionRepository {
    suspend fun getDefinition(word: String): Flow<Resource<List<DefinitionPresentationModelItem>>>
}