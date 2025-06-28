package com.example.happybirthday.marsphoto.data

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}


class NetworkMarsPhotosRepository(private val apiService: MarsApiService) : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> = apiService.getPhotos()
}