package com.example.instacrypto.di

import com.example.data.repository.RemoteDataSource
import com.example.data.repository.RepositoryImp
import com.example.domain.repository.Repository
import com.example.remote.source.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Module that holds Repository classes
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {


    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun provideRepository(repository : RepositoryImp) : Repository

}