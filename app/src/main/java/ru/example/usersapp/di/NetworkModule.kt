package ru.example.usersapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import ru.example.usersapp.BuildConfig
import ru.example.usersapp.data.remote.EmployeeApi
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class BaseUrl

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @BaseUrl
    @Provides
    @Singleton
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient().newBuilder().addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS).build()

    @Provides
    @Singleton
    fun provideJson() = Json { ignoreUnknownKeys = true }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideJsonFactory(json: Json) = json.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun provideRxCallAdapterFactory(): RxJava3CallAdapterFactory = RxJava3CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        rxCallAdapterFactory: RxJava3CallAdapterFactory,
        converterFactory: Converter.Factory,
        @BaseUrl baseUrl: String
    ): Retrofit = Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl)
        .addCallAdapterFactory(rxCallAdapterFactory).addConverterFactory(converterFactory).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): EmployeeApi = retrofit.create(EmployeeApi::class.java)
}