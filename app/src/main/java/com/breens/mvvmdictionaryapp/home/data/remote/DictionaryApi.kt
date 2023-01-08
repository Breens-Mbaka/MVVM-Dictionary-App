package com.breens.mvvmdictionaryapp.home.data.remote

import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("api/v2/entries/en/{word}")
    suspend fun getDefinition(@Path("word") word: String): NetworkResponse<List<DefinitionResponseModel>, ErrorResponse>
}