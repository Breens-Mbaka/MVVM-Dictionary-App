package com.breens.mvvmdictionaryapp.di

import com.breens.mvvmdictionaryapp.home.data.remote.DictionaryApi
import com.breens.mvvmdictionaryapp.home.data.remote.DictionaryRemoteDataSource
import com.breens.mvvmdictionaryapp.home.data.remote.DictionaryRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        dictionaryApi: DictionaryApi,
        @Dispatcher(DictionaryAppDispatchers.IO) ioDispatcher: CoroutineDispatcher
    ): DictionaryRemoteDataSource {
        return DictionaryRemoteDataSourceImpl(
            dictionaryApi = dictionaryApi,
            ioDispatcher = ioDispatcher
        )
    }
}