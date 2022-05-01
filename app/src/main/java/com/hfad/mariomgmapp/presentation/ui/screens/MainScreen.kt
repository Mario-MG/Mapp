package com.hfad.mariomgmapp.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.hfad.mariomgmapp.domain.model.TransportType
import com.hfad.mariomgmapp.domain.model.TransportType.*
import com.hfad.mariomgmapp.presentation.ui.MainViewModel
import com.hfad.mariomgmapp.presentation.ui.theme.AppTheme

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val loading = viewModel.loading
    val transports = viewModel.transports
    val initialLatLngBounds = LatLngBounds(viewModel.swBounds, viewModel.neBounds)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialLatLngBounds.center, 15f) // TODO: Find out how to zoom in to the frame
    }
    LaunchedEffect(viewModel) {
        snapshotFlow { cameraPositionState.position }
            .collect {
                val newLatLngBounds = cameraPositionState.projection?.visibleRegion?.latLngBounds
                viewModel.updateBounds(
                    swBounds = newLatLngBounds?.southwest,
                    neBounds = newLatLngBounds?.northeast
                )
            }
    }
    AppTheme(displayProgressIndicator = loading) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            transports.forEach { transport ->
                Marker(
                    state = MarkerState(position = LatLng(transport.lat, transport.lon)),
                    title = transport.type.toString(),
                    snippet = transport.info,
                    icon = getIconForTransportType(transport.type)
                )
            }
        }
    }
}

private fun getIconForTransportType(transportType: TransportType): BitmapDescriptor {
    return when (transportType) {
        MOPED -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
        BIKE_STATION -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
        UNKNOWN -> BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)
    }
}