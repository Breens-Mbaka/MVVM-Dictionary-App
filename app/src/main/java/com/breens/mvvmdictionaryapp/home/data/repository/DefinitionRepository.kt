package com.breens.mvvmdictionaryapp.home.data.repository

import com.breens.mvvmdictionaryapp.common.Resource
import com.breens.mvvmdictionaryapp.home.data.remote.DefinitionPresentationModelItem
import com.breens.mvvmdictionaryapp.home.data.remote.DictionaryRemoteDataSource
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class DefinitionRepository @Inject constructor(
    private val dictionaryRemoteDataSource: DictionaryRemoteDataSource
) {
    suspend fun getDefinition(word: String): Flow<Resource<List<DefinitionPresentationModelItem>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = dictionaryRemoteDataSource
                    .getDefinition(word = word).map { definitionResponseModel ->
                        definitionResponseModel.toPresentation()
                    }
                emit(Resource.Success(data = response))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Please check if you're connected to the internet"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.message ?: "Sorry something went wrong!"))
            }
        }
}