package com.example.happybirthday.marsphoto.data

// No need to copy over
sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Loading : MarsUiState
    object Error : MarsUiState
}