package ru.example.usersapp.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ru.example.usersapp.ui.MainActivity

@InstallIn(ActivityComponent::class)
@Module
class ActivityModule {
    @Provides
    fun provideRootActivity(activity: Activity) = activity as MainActivity
}