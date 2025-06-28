package com.example.marsphotos.network

// No need to copy over
sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Loading : MarsUiState
    object Error : MarsUiState
}