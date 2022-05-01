package com.hfad.mariomgmapp.network.repositories

import com.hfad.mariomgmapp.domain.model.Transport
import com.hfad.mariomgmapp.interactors.repositories.MarkerRepository
import com.hfad.mariomgmapp.network.model.MarkerDto
import com.hfad.mariomgmapp.network.services.MarkerService

class MarkerRepositoryImpl(
    private val markerService: MarkerService
) : MarkerRepository {

    override suspend fun getForFrame(
        lowerLeftLatLong: Pair<Double, Double>,
        upperRightLatLon: Pair<Double, Double>
    ): List<Transport> {
        return markerService.getForFrame(
            lowerLeftLatLong = "${lowerLeftLatLong.first},${lowerLeftLatLong.second}",
            upperRightLatLon = "${upperRightLatLon.first},${upperRightLatLon.second}"
        ).map(MarkerDto::toDomainModel)
    }

}