package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.happybirthday.data.DogDatasource
import com.example.happybirthday.model.Dog
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class WoofActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                WoofApp()
            }
        }
    }
}

@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        //处理Card颜色bug
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(bottomStart = 16.dp, topEnd = 16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Dogicon(dog)
            DogInformation(dog)
        }
    }
}

@Composable
fun DogInformation(dog: Dog, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(dog.name),
            style = MaterialTheme.typography.displayLarge,
        )
        Text(
            text = "${dog.age} years old",
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
fun Dogicon(dog: Dog, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(dog.imageResourceId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small)


    )
}


@Composable
fun WoofApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            WoofTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(DogDatasource.dogs) {
                DogItem(
                    dog = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
    @OptIn(ExperimentalMaterial3Api::class)
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .clip(CircleShape),
                    painter = painterResource(R.drawable.physics),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Woof",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    HappyBirthdayTheme {
        WoofApp()
    }
}

