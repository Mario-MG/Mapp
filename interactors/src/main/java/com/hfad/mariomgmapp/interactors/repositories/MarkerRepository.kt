package com.hfad.mariomgmapp.interactors.repositories

import com.hfad.mariomgmapp.domain.model.Transport

interface MarkerRepository {

    suspend fun getForFrame(
        lowerLeftLatLong: Pair<Double, Double>,
        upperRightLatLon: Pair<Double, Double>
    ): List<Transport>

}