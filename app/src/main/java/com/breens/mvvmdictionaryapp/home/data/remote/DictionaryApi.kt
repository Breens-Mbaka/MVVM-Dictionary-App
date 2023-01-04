package com.breens.mvvmdictionaryapp.home.data.remote

import com.breens.mvvmdictionaryapp.common.Resource
import com.breens.mvvmdictionaryapp.home.data.remote.model.DefinitionApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("api/v2/entries/en/{word}")
    fun getDefinition(@Path("word") word: String) : DefinitionApiModel
}