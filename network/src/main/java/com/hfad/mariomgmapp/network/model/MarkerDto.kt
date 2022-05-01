package com.hfad.mariomgmapp.network.model

import com.google.gson.annotations.SerializedName
import com.hfad.mariomgmapp.domain.model.Transport
import com.hfad.mariomgmapp.domain.model.TransportType.*

data class MarkerDto(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("x") var posx: Double,
    @SerializedName("y") var posy: Double,
    @SerializedName("realTimeData") var realTimeData: Boolean,
    @SerializedName("companyZoneId") var companyZoneId: Int,
    @SerializedName("licencePlate") var licencePlate: String?,
    @SerializedName("range") var range: Int?,
    @SerializedName("batteryLevel") var batteryLevel: Int?,
    @SerializedName("helmet") var helmet: Int?,
    @SerializedName("model") var model: String?,
    @SerializedName("resourceImageId") var resourceImageId: String?,
    @SerializedName("resourceImageUrls") var resourceImageUrls: List<String>?,
    @SerializedName("resourceType") var resourceType: String?,
    @SerializedName("station") var station: Boolean?,
    @SerializedName("availableResources") var availableResources: Int?,
    @SerializedName("spacesAvailable") var spacesAvailable: Int?,
    @SerializedName("allowDropoff") var allowDropoff: Boolean?,
    @SerializedName("bikesAvailable") var bikesAvailable: Int?,
) {
    fun toDomainModel(): Transport {
        return Transport(
            lat = posy,
            lon = posx,
            type = when (companyZoneId) {
                473 -> MOPED
                412 -> BIKE_STATION
                else -> UNKNOWN
            },
            info = when (companyZoneId) {
                473 -> "$licencePlate | $batteryLevel%"
                412 -> "Bikes/Spaces: $bikesAvailable/$spacesAvailable"
                else -> "No info"
            }
        )
    }
}
