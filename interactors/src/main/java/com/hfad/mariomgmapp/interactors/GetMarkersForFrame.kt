package com.hfad.mariomgmapp.interactors

import com.hfad.bankapp.interactors.data.DataState
import com.hfad.bankapp.interactors.data.DataState.*
import com.hfad.mariomgmapp.domain.model.Transport
import com.hfad.mariomgmapp.interactors.repositories.MarkerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMarkersForFrame(
    private val markerRepository: MarkerRepository
) {

    fun execute(
        lowerLeftLatLong: Pair<Double, Double>,
        upperRightLatLon: Pair<Double, Double>
    ): Flow<DataState<List<Transport>>> = flow {
        emit(Loading(true))
        try {
            val markers = markerRepository.getForFrame(
                lowerLeftLatLong = lowerLeftLatLong,
                upperRightLatLon = upperRightLatLon
            )
            emit(Success(markers))
        } catch (e: Exception) {
            emit(Error("Network error"))
        } finally {
            emit(Loading(false))
        }
    }

}