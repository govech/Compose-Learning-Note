package com.example.happybirthday.marsphoto.data

// No need to copy over
sealed interface MarsUiState {
    data class Success(val photo: MarsPhoto) : MarsUiState
    object Loading : MarsUiState
    object Error : MarsUiState
}