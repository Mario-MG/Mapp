package com.hfad.mariomgmapp.interactors.util

import com.hfad.bankapp.interactors.data.DataState
import com.hfad.bankapp.interactors.data.DataState.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

fun <T> Flow<DataState<T>>.on(
    loading: (Boolean) -> Unit = {},
    error: (String) -> Unit = {},
    success: (T) -> Unit = {}
): Flow<DataState<T>> {
    return this.onEach { dataState ->
        when (dataState) {
            is Loading -> loading(dataState.loading)
            is Error -> error(dataState.error)
            is Success -> success(dataState.data)
        }
    }
}