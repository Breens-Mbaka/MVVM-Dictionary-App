package com.breens.mvvmdictionaryapp.home.data

import com.breens.mvvmdictionaryapp.model.DefinitionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("api/v2/entries/en/{word}")
    fun getDefinition(@Path("word") word: String) : Result<DefinitionResponse>
}