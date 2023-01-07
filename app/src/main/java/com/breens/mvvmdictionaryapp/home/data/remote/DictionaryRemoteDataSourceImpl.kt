package com.breens.mvvmdictionaryapp.home.data.remote

import com.breens.mvvmdictionaryapp.di.DictionaryAppDispatchers
import com.breens.mvvmdictionaryapp.di.Dispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DictionaryRemoteDataSourceImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi,
    @Dispatcher(DictionaryAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
): DictionaryRemoteDataSource {

    override suspend fun getDefinition(word: String) : List<DefinitionResponseModel> =
        withContext(ioDispatcher) {
            dictionaryApi.getDefinition(word = word)
        }
}