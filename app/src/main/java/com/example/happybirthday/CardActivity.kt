package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class CardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
                ) {
                    ZZZ()
                }
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    TopTitle(modifier = Modifier.padding(innerPadding))
//                }
            }
        }
    }
}

@Composable
fun TopTitle(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_task_completed),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
        Text(
            text = "Jennifer Doe",
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 5.dp)
        )
        Text(
            text = "Android Developer Extraordinaire",
            color = Color(0xFF0F6428),
            fontSize = 14.sp,
            modifier = Modifier
        )
    }
}


@Composable
fun IconBottom(modifier: Modifier = Modifier) {
    Column{
        Row {
            Icon(
                imageVector = Icons.Filled.Call,
                contentDescription = null,
                tint = Color(0xFF0F6428),
                modifier = Modifier
                    .padding(top = 5.dp)
                    .size(24.dp)
            )
            Text(
                text = "123-456-7890",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 5.dp, start = 5.dp)
            )
        }


        Row {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = null,
                tint = Color(0xFF0F6428),
                modifier = Modifier
                    .padding(top = 5.dp)
                    .size(24.dp)
            )
            Text(
                text = "jennifer@example.com",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 5.dp, start = 5.dp)
            )
        }

        Row {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = null,
                tint = Color(0xFF0F6428),
                modifier = Modifier
                    .padding(top = 5.dp)
                    .size(24.dp)
            )
            Text(
                text = "jennifer@example.com",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 5.dp, start = 5.dp)
            )
        }


    }
}

@Composable
fun ZZZ(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopTitle(
            modifier = modifier.weight(1f)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            IconBottom(
                modifier = modifier.padding(bottom = 15.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    HappyBirthdayTheme {
        Surface(
//            modifier = Modifier.fillMaxSize()
        ) {
            ZZZ()
        }

//        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//            ZZZ(modifier = Modifier.padding(innerPadding))
//        }

    }
}