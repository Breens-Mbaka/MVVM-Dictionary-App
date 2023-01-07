package com.breens.mvvmdictionaryapp.di

import com.breens.mvvmdictionaryapp.home.data.remote.DictionaryRemoteDataSource
import com.breens.mvvmdictionaryapp.home.data.repository.DefinitionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDefinitionRepositoryImpl(
        dictionaryRemoteDataSource: DictionaryRemoteDataSource
    ): DefinitionRepository {
        return DefinitionRepository(dictionaryRemoteDataSource = dictionaryRemoteDataSource)
    }
}