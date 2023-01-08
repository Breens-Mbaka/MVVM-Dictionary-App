package com.breens.mvvmdictionaryapp.home.data.remote

import com.haroldadmin.cnradapter.NetworkResponse

interface DictionaryRemoteDataSource {
    suspend fun getDefinition(word: String): NetworkResponse<List<DefinitionResponseModel>, ErrorResponse>
}