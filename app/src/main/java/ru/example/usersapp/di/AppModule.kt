package ru.example.usersapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.example.usersapp.R
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class UnknownEmployeeIDMessage
@Qualifier
annotation class UnknownSpecialtyIDMessage

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @UnknownEmployeeIDMessage
    @Provides
    @Singleton
    fun provideUnknownEmployeeIdMessage(@ApplicationContext context: Context): String = context.getString(R.string.unknownEmployeeId)

    @UnknownSpecialtyIDMessage
    @Provides
    @Singleton
    fun provideUnknownSpecialtyIdMessage(@ApplicationContext context: Context): String = context.getString(R.string.unknownSpecialtyId)
}