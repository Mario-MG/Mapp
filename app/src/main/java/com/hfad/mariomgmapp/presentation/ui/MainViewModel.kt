package com.hfad.mariomgmapp.presentation.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.hfad.mariomgmapp.domain.model.Transport
import com.hfad.mariomgmapp.interactors.GetMarkersForFrame
import com.hfad.mariomgmapp.interactors.util.on
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val getMarkersForFrame: GetMarkersForFrame
) : ViewModel() {

    var loading by mutableStateOf(false)
        private set

    val transports = mutableStateListOf<Transport>()

    var swBounds = LatLng(38.711046, -9.160096)
        private set

    var neBounds = LatLng(38.739429, -9.137115)
        private set

    init {
        loadMarkers()
    }

    private fun loadMarkers() {
        getMarkersForFrame
            .execute(
                lowerLeftLatLong = Pair(swBounds.latitude, swBounds.longitude),
                upperRightLatLon = Pair(neBounds.latitude, neBounds.longitude)
            )
            .on(
                loading = ::loading::set,
                success = { markers ->
                    this.transports.clear()
                    this.transports.addAll(markers)
                },
                error = { error -> Log.d("ERROR", error) } // TODO: Show dialog?
            )
            .launchIn(viewModelScope)
    }

    fun updateBounds(
        swBounds: LatLng?,
        neBounds: LatLng?
    ) {
        swBounds?.let { this.swBounds = it }
        neBounds?.let { this.neBounds = it }
        loadMarkers()
    }

}