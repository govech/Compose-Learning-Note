package com.example.happybirthday.fake

import com.example.happybirthday.marsphoto.data.MarsApiService
import com.example.happybirthday.marsphoto.data.MarsPhoto

class FakeMarsApiService : MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}