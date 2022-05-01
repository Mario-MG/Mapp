package com.hfad.mariomgmapp.domain.model

data class Transport(
    val lat: Double,
    val lon: Double,
    val type: TransportType,
    val info: String
)