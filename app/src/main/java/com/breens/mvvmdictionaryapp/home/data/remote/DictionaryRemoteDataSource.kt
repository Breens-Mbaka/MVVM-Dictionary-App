package com.breens.mvvmdictionaryapp.home.data.remote

interface DictionaryRemoteDataSource {
    suspend fun getDefinition(word: String) : List<DefinitionResponseModel>
}