package com.breens.mvvmdictionaryapp.home.data.repository

import com.breens.mvvmdictionaryapp.common.Resource
import com.breens.mvvmdictionaryapp.home.data.remote.DictionaryApi
import com.breens.mvvmdictionaryapp.home.data.remote.model.DefinitionApiModel
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class DefinitionRepositoryImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi
) : DefinitionRepository {
    override suspend fun getDefinition(word: String): Flow<Resource<DefinitionApiModel>> = flow {
        emit(Resource.Loading())
        try {
            val response = dictionaryApi.getDefinition(word = word)
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Please check if you're connected to the internet"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message?:"Sorry something went wrong!"))
        }
    }
}