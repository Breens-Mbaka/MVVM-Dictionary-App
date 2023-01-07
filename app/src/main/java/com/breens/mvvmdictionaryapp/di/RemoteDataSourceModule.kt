package com.breens.mvvmdictionaryapp.di

import com.breens.mvvmdictionaryapp.home.data.remote.DictionaryApi
import com.breens.mvvmdictionaryapp.home.data.remote.DictionaryRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        dictionaryApi: DictionaryApi,
        @Dispatcher(DictionaryAppDispatchers.IO) ioDispatcher: CoroutineDispatcher
    ): DictionaryRemoteDataSource {
        return DictionaryRemoteDataSource(dictionaryApi = dictionaryApi, ioDispatcher = ioDispatcher)
    }
}