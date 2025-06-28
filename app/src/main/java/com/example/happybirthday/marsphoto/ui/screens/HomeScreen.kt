/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.happybirthday.R
import com.example.happybirthday.marsphoto.data.MarsPhoto
import com.example.happybirthday.marsphoto.data.MarsUiState
import com.example.happybirthday.marsphoto.ui.theme.HappyBirthdayTheme


@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
//        is MarsUiState.Success -> ResultScreen(
//            marsUiState.photos, modifier = modifier.fillMaxWidth()
//        )
        is MarsUiState.Success -> MarsPhotoCard(
            photo = marsUiState.photo,
            modifier = modifier.fillMaxSize()
        )


        is MarsUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
//    ResultScreen(marsUiState, modifier.padding(top = contentPadding.calculateTopPadding()))
}

const val Murl =
    "https://www.wikihow.com/images/thumb/2/21/Get-the-URL-for-Pictures-Step-5-Version-3.jpg/v4-728px-Get-the-URL-for-Pictures-Step-5-Version-3.jpg.webp"

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {

        Text(text = photos)

    }
}


@Composable
fun MarsPhotoCard(photo: MarsPhoto, modifier: Modifier = Modifier) {
    Log.d("MarsPhotoCard", "Image URL = ${photo.imgSrc}")
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(photo.imgSrc)
            .crossfade(true)
            .listener(
                onError = { request, result ->
                    Log.e("MarsPhotoCard", "Image load failed", result.throwable)
                }
            )
            .build(),
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.mars_photo),
        contentScale = ContentScale.Crop,
        modifier = modifier

    )
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    HappyBirthdayTheme {
        MarsPhotoCard(
            photo = MarsPhoto(
                id = "1",
                imgSrc = "https://www.wikihow.com/images/thumb/2/21/Get-the-URL-for-Pictures-Step-5-Version-3.jpg/v4-728px-Get-the-URL-for-Pictures-Step-5-Version-3.jpg.webp"
            )
        )
    }
}
