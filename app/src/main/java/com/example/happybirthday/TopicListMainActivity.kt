package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.happybirthday.data.DataSourceTopic
import com.example.happybirthday.model.Topic
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class TopicListMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                )
                { innerPadding ->
                    TopicGrid(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TopCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(68.dp)

    ) {
        Row(
            modifier = Modifier
                .background(Color(212, 197, 224, 255))
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(topic.drawableRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(68.dp)
                    .fillMaxSize()
            )
            Column(
                modifier = modifier
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)

            ) {
                Text(
                    text = stringResource(topic.topicRes),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = topic.number.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }


            }

        }

    }

}


@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        // 设置网格列数（三种方式）
        columns = GridCells.Fixed(2), // 固定2列
        // columns = GridCells.Adaptive(128.dp) // 自适应列宽（最小128dp）
        // columns = GridCells.FixedSize(120.dp) // 固定项目大小（非固定列数）

        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        val dataList = DataSourceTopic.topics
        items(dataList) { item ->
            TopCard(item)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicListPreview() {
    HappyBirthdayTheme {
        TopicGrid()
    }
}