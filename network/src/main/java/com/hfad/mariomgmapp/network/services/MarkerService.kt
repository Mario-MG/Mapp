package com.hfad.mariomgmapp.network.services

import com.hfad.mariomgmapp.network.model.MarkerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MarkerService {

    @GET("resources")
    suspend fun getForFrame(
        @Query("lowerLeftLatLong") lowerLeftLatLong: String,
        @Query("upperRightLatLon") upperRightLatLon: String
    ): List<MarkerDto>

}