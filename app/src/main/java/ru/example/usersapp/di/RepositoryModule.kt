package ru.example.usersapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.example.usersapp.data.repositories.RepositoryImpl
import ru.example.usersapp.domain.Repository
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindRepository(impl: RepositoryImpl): Repository
}