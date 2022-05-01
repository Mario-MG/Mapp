package com.hfad.mariomgmapp.domain.model

enum class TransportType(
    private val description: String
) {
    MOPED("MOPED"),
    BIKE_STATION("BIKE STATION"),
    UNKNOWN("UNKOWN");

    override fun toString(): String {
        return description
    }
}