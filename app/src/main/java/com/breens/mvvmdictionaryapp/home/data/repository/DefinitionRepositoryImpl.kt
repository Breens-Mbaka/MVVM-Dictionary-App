package com.breens.mvvmdictionaryapp.home.data.repository

import com.breens.mvvmdictionaryapp.common.Resource
import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionPresentationModelItem
import com.breens.mvvmdictionaryapp.home.data.remote.DictionaryRemoteDataSource
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefinitionRepositoryImpl @Inject constructor(
    private val dictionaryRemoteDataSource: DictionaryRemoteDataSource
) : DefinitionRepository {
    override suspend fun getDefinition(word: String): Flow<Resource<List<DefinitionPresentationModelItem>>> =
        flow {
            emit(Resource.Loading())
            when (val response = dictionaryRemoteDataSource.getDefinition(word = word)) {
                is NetworkResponse.Success -> {
                    val definitionResponse = response.body.map { definitionResponseModel ->
                        definitionResponseModel.toPresentation()
                    }
                    emit(Resource.Success(data = definitionResponse))
                }
                is NetworkResponse.ServerError -> {
                    emit(
                        Resource.Error(
                            message = response.body?.message ?: "Try again with a new word!"
                        )
                    )
                }
                is NetworkResponse.NetworkError -> {
                    emit(Resource.Error(message = "Please check if you're connected to the internet"))
                }
                is NetworkResponse.UnknownError -> {
                    emit(Resource.Error(message = "Unknown error occurred while fetching definition"))
                }
            }
        }
}