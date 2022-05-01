package com.hfad.mariomgmapp.network.repositories

import com.hfad.mariomgmapp.domain.model.Transport
import com.hfad.mariomgmapp.domain.model.TransportType
import com.hfad.mariomgmapp.interactors.repositories.MarkerRepository

class FakeMarkerRepositoryImpl : MarkerRepository {
    private val transports = listOf(
        Transport(
            lat = 38.721111,
            lon = -9.151111,
            type = TransportType.MOPED,
            info = "Fake info"
        ),
        Transport(
            lat = 38.722222,
            lon = -9.1522222,
            type = TransportType.MOPED,
            info = "Fake info"
        ),
        Transport(
            lat = 38.741111,
            lon = -9.151111,
            type = TransportType.MOPED,
            info = "Fake info"
        ),
        Transport(
            lat = 38.722222,
            lon = -9.1722222,
            type = TransportType.MOPED,
            info = "Fake info"
        )
    )

    override suspend fun getForFrame(
        lowerLeftLatLong: Pair<Double, Double>,
        upperRightLatLon: Pair<Double, Double>
    ): List<Transport> {
        return transports.filter { transport ->
            transport.lat > lowerLeftLatLong.first
                    && transport.lat < upperRightLatLon.first
                    && transport.lon > lowerLeftLatLong.second
                    && transport.lon < upperRightLatLon.second
        }
    }

}