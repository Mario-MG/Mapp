package com.hfad.mariomgmapp.di

import com.google.gson.GsonBuilder
import com.hfad.mariomgmapp.interactors.repositories.MarkerRepository
import com.hfad.mariomgmapp.network.repositories.FakeMarkerRepositoryImpl
import com.hfad.mariomgmapp.network.repositories.MarkerRepositoryImpl
import com.hfad.mariomgmapp.network.services.MarkerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRefrofitClient( ): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideMarkerService(
        retrofitClient: OkHttpClient
    ): MarkerService {
        return Retrofit.Builder()
            .baseUrl("https://apidev.meep.me/tripplan/api/v1/routers/lisboa/")
            .client(retrofitClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MarkerService::class.java)
    }

    @Provides
    @Singleton
    fun provideMarkerRepository(
        markerService: MarkerService
    ): MarkerRepository {
        return MarkerRepositoryImpl(
            markerService = markerService
        )
//        return FakeMarkerRepositoryImpl()
    }

}