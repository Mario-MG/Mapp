package com.hfad.mariomgmapp.di

import com.hfad.mariomgmapp.interactors.GetMarkersForFrame
import com.hfad.mariomgmapp.interactors.repositories.MarkerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {

    @Provides
    @ViewModelScoped
    fun provideGetMarkersForFrame(
        markerRepository: MarkerRepository
    ): GetMarkersForFrame {
        return GetMarkersForFrame(
            markerRepository = markerRepository
        )
    }

}